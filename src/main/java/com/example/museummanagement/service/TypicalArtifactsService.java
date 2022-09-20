package com.example.museummanagement.service;

import com.example.museummanagement.entity.TypicalArtifacts;
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

    List<TypicalArtifacts> getAllTypicalArtifacts();
}
