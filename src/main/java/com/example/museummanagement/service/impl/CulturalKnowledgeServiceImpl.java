package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CulturalKnowledgeDTO;
import com.example.museummanagement.entity.CulturalKnowledge;
import com.example.museummanagement.repository.CulturalKnowledgeRepository;
import com.example.museummanagement.service.CulturalKnowledgeService;
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
public class CulturalKnowledgeServiceImpl implements CulturalKnowledgeService {

    private final CulturalKnowledgeRepository culturalKnowledgeRepository;

    @SneakyThrows
    @Transactional
    @Override
    public CulturalKnowledgeDTO createCulturalKnowledge(CulturalKnowledgeDTO culturalKnowledgeDTO) {
        Optional<CulturalKnowledge> optionalCulturalKnowledge = culturalKnowledgeRepository.findByName(culturalKnowledgeDTO.getName());
        CulturalKnowledge culturalKnowledge = new CulturalKnowledge();
        if (optionalCulturalKnowledge.isEmpty()){
            culturalKnowledge.setType(Constants.TYPE_CULTURAL_KNOWLEDGE);
            culturalKnowledge.setName(culturalKnowledgeDTO.getName());
            culturalKnowledge.setTitle(culturalKnowledgeDTO.getTitle());
            culturalKnowledge.setSlug(culturalKnowledgeDTO.getSlug());
            culturalKnowledge.setContent(culturalKnowledgeDTO.getContent());
            culturalKnowledge.setAuthor(culturalKnowledgeDTO.getAuthor());
            culturalKnowledge.setStatus(Constants.STATUS_ACTIVE);
            culturalKnowledgeRepository.save(culturalKnowledge);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return culturalKnowledgeDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public CulturalKnowledgeDTO updateCulturalKnowledge(CulturalKnowledgeDTO culturalKnowledgeDTO, Long id) {
        Optional<CulturalKnowledge> optionalCulturalKnowledge = culturalKnowledgeRepository.findById(id);
        if (optionalCulturalKnowledge.isPresent()) {
            CulturalKnowledge culturalKnowledge = optionalCulturalKnowledge.get();
            Optional<CulturalKnowledge> culturalKnowledgeOpt = culturalKnowledgeRepository.findByName(culturalKnowledgeDTO.getName());
            if (culturalKnowledgeOpt.isEmpty() || culturalKnowledge.getId().equals(culturalKnowledgeOpt.get().getId())){
                culturalKnowledge.setType(Constants.TYPE_CULTURAL_KNOWLEDGE);
                culturalKnowledge.setName(culturalKnowledgeDTO.getName());
                culturalKnowledge.setTitle(culturalKnowledgeDTO.getTitle());
                culturalKnowledge.setSlug(culturalKnowledgeDTO.getSlug());
                culturalKnowledge.setContent(culturalKnowledgeDTO.getContent());
                culturalKnowledge.setAuthor(culturalKnowledgeDTO.getAuthor());
                culturalKnowledge.setStatus(Constants.STATUS_ACTIVE);
                culturalKnowledgeRepository.save(culturalKnowledge);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found id: " + id);
        }
        return culturalKnowledgeDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteCulturalKnowledge(Long id) {
        List<CulturalKnowledge> culturalKnowledge = culturalKnowledgeRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(culturalKnowledge)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (CulturalKnowledge culturalKnowledges : culturalKnowledge) {
            culturalKnowledges.setStatus(Constants.STATUS_INACTIVE);
            culturalKnowledges.setModifiedDate(new Date());
            culturalKnowledgeRepository.save(culturalKnowledges);
        }
    }

    @Override
    public Page <CulturalKnowledge> findAllCulturalKnowledge(Pageable pageable, CulturalKnowledgeDTO culturalKnowledgeDTO) {
        String search;
        if (StringUtils.isEmpty(culturalKnowledgeDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + culturalKnowledgeDTO.getSearch().toLowerCase() + "%";
        }
        return culturalKnowledgeRepository.findAllBySearch(pageable, search);
    }
}
