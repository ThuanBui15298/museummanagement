package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Collections;
import com.example.museummanagement.entity.ItinerantDisplay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionsRepository extends JpaRepository<Collections, Long> {

    Optional<Collections> findByName(String name);

    List<Collections> findAllByIdAndStatus(Long id, Integer status);
}
