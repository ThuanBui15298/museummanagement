package com.example.museummanagement.repository;

import com.example.museummanagement.entity.ArchaeologicalExcavations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ArchaeologicalExcavationsRepository extends JpaRepository<ArchaeologicalExcavations, Long> {

    Optional<ArchaeologicalExcavations> findByName(String name);

    List<ArchaeologicalExcavations> findAllByIdAndStatus(Long id, Integer status);
}
