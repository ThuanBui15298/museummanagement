package com.example.museummanagement.repository;

import com.example.museummanagement.entity.TypicalArtifacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface TypicalArtifactsRepository extends JpaRepository<TypicalArtifacts, Long> {
    Optional<TypicalArtifacts> findByName(String name);

    List<TypicalArtifacts> findAllByIdAndStatus(Long id, Integer status);
}
