package com.example.museummanagement.service;

import com.example.museummanagement.dto.SightSeeingRuleDTO;
import com.example.museummanagement.entity.SightSeeingRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface SightSeeingRuleService {

    @Transactional
    SightSeeingRuleDTO createSightSeeingRule(SightSeeingRuleDTO sightSeeingRuleDTO);

    @Transactional
    SightSeeingRuleDTO updateSightSeeingRule(SightSeeingRuleDTO sightSeeingRuleDTO, Long id);

    void deleteSightSeeingRule(Long id);

    List<SightSeeingRule> getAllSightSeeingRule();
}
