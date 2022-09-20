package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CulturalKnowledgeDTO;
import com.example.museummanagement.entity.CulturalKnowledge;
import com.example.museummanagement.repository.CulturalKnowledgeRepository;
import com.example.museummanagement.service.CulturalKnowledgeService;
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
        CulturalKnowledge culturalKnowledge = optionalCulturalKnowledge.get();
        if (optionalCulturalKnowledge.isPresent()) {
            Optional<CulturalKnowledge> culturalKnowledgeOpt = culturalKnowledgeRepository.findByName(culturalKnowledgeDTO.getName());
            if (culturalKnowledgeOpt.isEmpty()){
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
            throw new MessageDescriptorFormatException("No existed");
        }
        for (CulturalKnowledge culturalKnowledges : culturalKnowledge) {
            culturalKnowledges.setStatus(Constants.STATUS_INACTIVE);
            culturalKnowledges.setModifiedDate(new Date());
            culturalKnowledgeRepository.save(culturalKnowledges);
        }
    }

    @Override
    public List<CulturalKnowledge> findAllCulturalKnowledge() {
        return culturalKnowledgeRepository.findAll();
    }
}
