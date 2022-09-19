package com.example.museummanagement.repository;

import com.example.museummanagement.entity.ThematicExhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ThematicExhibitionRepository extends JpaRepository<ThematicExhibition, Long> {

    Optional<ThematicExhibition> findByName(String name);


    List<ThematicExhibition> findAllByIdAndStatus(Long id, Integer status);
}
