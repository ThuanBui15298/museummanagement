package com.example.museummanagement.repository;

import com.example.museummanagement.dto.ItinerantDisplayDTO;
import com.example.museummanagement.entity.ItinerantDisplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ItinerantDisplayRepository extends JpaRepository<ItinerantDisplay, Long> {
    Optional<ItinerantDisplay> findByName(String name);

    List<ItinerantDisplay> findAllByIdAndStatus(Long id, Integer status);
}
