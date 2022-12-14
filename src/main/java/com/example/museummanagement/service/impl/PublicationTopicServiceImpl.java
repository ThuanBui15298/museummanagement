package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.PublicationTopicDTO;
import com.example.museummanagement.entity.PublicationTopic;
import com.example.museummanagement.repository.PublicationTopicRepository;
import com.example.museummanagement.service.PublicationTopicService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationTopicServiceImpl implements PublicationTopicService {

    private final PublicationTopicRepository publicationTopicRepository;

    @SneakyThrows
    @Transactional
    @Override
    public PublicationTopicDTO createPublicationTopic(PublicationTopicDTO publicationTopicDTO) {
        Optional<PublicationTopic> optionalPublicationTopic = publicationTopicRepository.findByName(publicationTopicDTO.getName());
        PublicationTopic publicationTopic = new PublicationTopic();
        if (optionalPublicationTopic.isEmpty()) {
            publicationTopic.setType(Constants.TYPE_PUBLICATION_TOPICS);
            publicationTopic.setName(publicationTopicDTO.getName());
            publicationTopic.setTitle(publicationTopicDTO.getTitle());
            publicationTopic.setSlug(publicationTopicDTO.getSlug());
            publicationTopic.setContent(publicationTopicDTO.getContent());
            publicationTopic.setAuthor(publicationTopicDTO.getAuthor());
            publicationTopic.setStatus(Constants.STATUS_ACTIVE);
            publicationTopicRepository.save(publicationTopic);
        } else {
            throw new Exception("Name existed!");
        }
        return publicationTopicDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public PublicationTopicDTO updatePublicationTopic(PublicationTopicDTO publicationTopicDTO, Long id) {
        Optional<PublicationTopic> optionalPublicationTopic = publicationTopicRepository.findById(id);
        if (optionalPublicationTopic.isPresent()) {
            PublicationTopic publicationTopic = optionalPublicationTopic.get();
            Optional<PublicationTopic> publicationTopics = publicationTopicRepository.findByName(publicationTopicDTO.getName());
            if (publicationTopics.isEmpty() || publicationTopic.getId().equals(publicationTopics.get().getId())) {
                publicationTopic.setType(Constants.TYPE_PUBLICATION_TOPICS);
                publicationTopic.setName(publicationTopicDTO.getName());
                publicationTopic.setTitle(publicationTopicDTO.getTitle());
                publicationTopic.setSlug(publicationTopicDTO.getSlug());
                publicationTopic.setContent(publicationTopicDTO.getContent());
                publicationTopic.setAuthor(publicationTopicDTO.getAuthor());
                publicationTopic.setStatus(Constants.STATUS_ACTIVE);
                publicationTopicRepository.save(publicationTopic);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found id: " + id);
        }
        return publicationTopicDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deletePublicationTopic(Long id) {
        List<PublicationTopic> publicationTopics = publicationTopicRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(publicationTopics)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (PublicationTopic publicationTopic : publicationTopics) {
            publicationTopic.setStatus(Constants.STATUS_INACTIVE);
            publicationTopic.setModifiedDate(new Date());
            publicationTopicRepository.save(publicationTopic);
        }
    }

    @Override
    public Page<PublicationTopic> findAllPublicationTopic(Pageable pageable, PublicationTopicDTO publicationTopicDTO) {
        String search;
        if (StringUtils.isEmpty(publicationTopicDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + publicationTopicDTO.getSearch().toLowerCase() + "%";
        }
        return publicationTopicRepository.findAllBySearch(pageable, search);
    }
}
