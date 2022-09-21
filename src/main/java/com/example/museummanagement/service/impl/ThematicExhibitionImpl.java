package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.ThematicExhibitionDTO;
import com.example.museummanagement.entity.ThematicExhibition;
import com.example.museummanagement.repository.ThematicExhibitionRepository;
import com.example.museummanagement.service.ThematicExhibitionService;
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
public class ThematicExhibitionImpl implements ThematicExhibitionService {

    private final ThematicExhibitionRepository thematicExhibitionRepository;

    @SneakyThrows
    @Transactional
    @Override
    public ThematicExhibitionDTO createThematicExhibition(ThematicExhibitionDTO thematicExhibitionDTO) {
        validRequest(thematicExhibitionDTO);
        Optional<ThematicExhibition> thematicExhibitionName = thematicExhibitionRepository.findByName(thematicExhibitionDTO.getName());
        ThematicExhibition thematicExhibition = new ThematicExhibition();
        if (thematicExhibitionName.isEmpty()) {
            thematicExhibition.setName(thematicExhibitionDTO.getName());
            thematicExhibition.setContent(thematicExhibitionDTO.getContent());
            thematicExhibition.setType(Constants.TYPE_THEMATIC_EXHIBITION);
            thematicExhibition.setTitle(thematicExhibitionDTO.getTitle());
            thematicExhibition.setSlug(thematicExhibitionDTO.getSlug());
            thematicExhibition.setAuthor(thematicExhibitionDTO.getAuthor());
            thematicExhibition.setStatus(Constants.STATUS_ACTIVE);
            thematicExhibitionRepository.save(thematicExhibition);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return thematicExhibitionDTO;
    }


    @SneakyThrows
    @Transactional
    @Override
    public ThematicExhibitionDTO updateThematicExhibition(ThematicExhibitionDTO thematicExhibitionDTO, Long id) {
        validRequest(thematicExhibitionDTO);
        Optional<ThematicExhibition> thematicExh = thematicExhibitionRepository.findById(id);
        if (thematicExh.isPresent()) {
            ThematicExhibition thematicExhibition = thematicExh.get();
            Optional<ThematicExhibition> thematicExhibitionName = thematicExhibitionRepository.findByName(thematicExhibitionDTO.getName());
            if (thematicExhibitionName.isEmpty() || thematicExhibition.getId().equals(thematicExhibitionName.get().getId())) {
                thematicExhibition.setName(thematicExhibitionDTO.getName());
                thematicExhibition.setContent(thematicExhibitionDTO.getContent());
                thematicExhibition.setType(Constants.TYPE_THEMATIC_EXHIBITION);
                thematicExhibition.setTitle(thematicExhibitionDTO.getTitle());
                thematicExhibition.setSlug(thematicExhibitionDTO.getSlug());
                thematicExhibition.setAuthor(thematicExhibitionDTO.getAuthor());
                thematicExhibition.setStatus(Constants.STATUS_ACTIVE);
                thematicExhibitionRepository.save(thematicExhibition);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found by id: " + id);
        }
        return thematicExhibitionDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteThematicExhibition(Long id) {
        List<ThematicExhibition> thematicExhibitions = thematicExhibitionRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(thematicExhibitions)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (ThematicExhibition thematicExhibition : thematicExhibitions) {
            thematicExhibition.setStatus(Constants.STATUS_INACTIVE);
            thematicExhibition.setModifiedDate(new Date());
            thematicExhibitionRepository.save(thematicExhibition);
        }
    }

    @Override
    public Page<ThematicExhibition> findAll(Pageable pageable, ThematicExhibitionDTO thematicExhibitionDTO) {
        String search;
        if (StringUtils.isEmpty(thematicExhibitionDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + thematicExhibitionDTO.getSearch().toLowerCase() + "%";
        }
        return thematicExhibitionRepository.findAllThematicExhibition(pageable, search);
    }

    @SneakyThrows
    private void validRequest(ThematicExhibitionDTO thematicExhibitionDTO) {
        if (thematicExhibitionDTO == null) {
            throw new MessageDescriptorFormatException("Request invalid");
        }
    }
}
