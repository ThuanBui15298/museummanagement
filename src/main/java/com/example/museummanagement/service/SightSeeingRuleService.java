package com.example.museummanagement.service;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.SightSeeingRuleDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.SightSeeingRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<SightSeeingRule> findAllSightSeeingRule(Pageable pageable, SightSeeingRuleDTO sightSeeingRuleDTO);

}
