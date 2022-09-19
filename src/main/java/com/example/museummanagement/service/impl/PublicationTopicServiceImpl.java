package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.PublicationTopicDTO;
import com.example.museummanagement.entity.PublicationTopic;
import com.example.museummanagement.repository.PublicationTopicRepository;
import com.example.museummanagement.service.PublicationTopicService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        if (optionalPublicationTopic.isEmpty()){
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
        PublicationTopic publicationTopic = optionalPublicationTopic.get();
        if (optionalPublicationTopic.isPresent()) {
            Optional<PublicationTopic> publicationTopics = publicationTopicRepository.findByName(publicationTopicDTO.getName());
            if (publicationTopics.isEmpty()){
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
            throw new MessageDescriptorFormatException("No existed");
        }
        for (PublicationTopic publicationTopic : publicationTopics) {
            publicationTopic.setStatus(Constants.STATUS_INACTIVE);
            publicationTopic.setModifiedDate(new Date());
            publicationTopicRepository.save(publicationTopic);
        }
    }

    @Override
    public List<PublicationTopic> findAllPublicationTopic() {
        return publicationTopicRepository.findAll();
    }
}
