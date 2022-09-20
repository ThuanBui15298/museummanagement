package com.example.museummanagement.repository;

import com.example.museummanagement.entity.SightSeeingGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface SightSeeingGuideRepository extends JpaRepository<SightSeeingGuide, Long>{
    Optional<SightSeeingGuide> findByName(String name);

    List<SightSeeingGuide> findAllByIdAndStatus(Long id, Integer status);
}

