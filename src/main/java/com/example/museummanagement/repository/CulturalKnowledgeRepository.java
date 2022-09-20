package com.example.museummanagement.repository;

import com.example.museummanagement.entity.CulturalKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CulturalKnowledgeRepository extends JpaRepository<CulturalKnowledge, Long> {

    Optional<CulturalKnowledge> findByName(String name);

    List<CulturalKnowledge> findAllByIdAndStatus(Long id, Integer status);

}
