package com.example.museummanagement.service;

import com.example.museummanagement.dto.CulturalKnowledgeDTO;
import com.example.museummanagement.entity.CulturalKnowledge;

import javax.transaction.Transactional;
import java.util.List;

public interface CulturalKnowledgeService {

    @Transactional
    CulturalKnowledgeDTO createCulturalKnowledge(CulturalKnowledgeDTO culturalKnowledgeDTO);

    @Transactional
    CulturalKnowledgeDTO updateCulturalKnowledge(CulturalKnowledgeDTO culturalKnowledgeDTO, Long id)  ;

    void deleteCulturalKnowledge(Long id) ;

    List<CulturalKnowledge> findAllCulturalKnowledge();
}


