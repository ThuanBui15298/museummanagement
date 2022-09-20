package com.example.museummanagement.service;

import com.example.museummanagement.dto.SightSeeingGuideDTO;
import com.example.museummanagement.entity.SightSeeingGuide;
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

    List<SightSeeingGuide> getAllSightSeeingGuide();
}
