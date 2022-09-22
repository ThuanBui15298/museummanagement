package com.example.museummanagement.service;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.TypicalArtifactsDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.TypicalArtifacts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface TypicalArtifactsService {
    @Transactional
    TypicalArtifacts createTypicalArtifact(TypicalArtifacts typicalArtifacts);

    @Transactional
    TypicalArtifacts updateTypicalArtifact(TypicalArtifacts typicalArtifacts, Long id);

    void deleteTypicalArtifact(Long id);

    Page<TypicalArtifacts> findAllTypicalArtifacts(Pageable pageable, TypicalArtifactsDTO typicalArtifactsDTO);
}
