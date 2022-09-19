package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.ThematicExhibitionDTO;
import com.example.museummanagement.entity.ThematicExhibition;
import com.example.museummanagement.repository.ThematicExhibitionRepository;
import com.example.museummanagement.service.ThematicExhibitionService;
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
            throw new MessageDescriptorFormatException("Name da ton tai");
        }
        return thematicExhibitionDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public ThematicExhibitionDTO updateThematicExhibition(ThematicExhibitionDTO thematicExhibitionDTO, Long id) {
        validRequest(thematicExhibitionDTO);
        Optional<ThematicExhibition> thematicExh = thematicExhibitionRepository.findById(id);
        ThematicExhibition thematicExhibition = thematicExh.get();
        if (thematicExh.isPresent()) {
            Optional<ThematicExhibition> thematicExhibitionName = thematicExhibitionRepository.findByName(thematicExhibitionDTO.getName());
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
                throw new MessageDescriptorFormatException("Name da ton tai");
            }
        } else {
            throw new MessageDescriptorFormatException("Id khong ton tai");
        }
        return thematicExhibitionDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteThematicExhibition(Long id) {
        List<ThematicExhibition> thematicExhibitions = thematicExhibitionRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(thematicExhibitions)) {
            throw new MessageDescriptorFormatException("Khong ton tai");
        }
        for (ThematicExhibition thematicExhibition : thematicExhibitions) {
            thematicExhibition.setStatus(Constants.STATUS_INACTIVE);
            thematicExhibition.setModifiedDate(new Date());
            thematicExhibitionRepository.save(thematicExhibition);
        }
    }

    @Override
    public List<ThematicExhibition> findAll() {
        return thematicExhibitionRepository.findAll();
    }

    @SneakyThrows
    private void validRequest(ThematicExhibitionDTO thematicExhibitionDTO) {
        if (thematicExhibitionDTO == null) {
            throw new MessageDescriptorFormatException("Request invalid");
        }
    }
}
