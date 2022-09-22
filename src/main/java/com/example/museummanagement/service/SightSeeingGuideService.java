package com.example.museummanagement.service;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.SightSeeingGuideDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.SightSeeingGuide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface SightSeeingGuideService {
    @Transactional
    SightSeeingGuideDTO createSightSeeingGuide(SightSeeingGuideDTO sightSeeingGuideDTO);

    @Transactional
    SightSeeingGuideDTO updateSightSeeingGuide(SightSeeingGuideDTO sightSeeingGuideDTO, Long id);

    void deleteSightSeeingGuide(Long id);

    Page<SightSeeingGuide> findAllSightSeeingGuide(Pageable pageable, SightSeeingGuideDTO sightSeeingGuideDTO);

}
