package com.example.museummanagement.repository;

import com.example.museummanagement.entity.NationalTreasures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface NationalTreasuresRepository extends JpaRepository<NationalTreasures, Long> {
    Optional<NationalTreasures> findByName(String name);

    List<NationalTreasures> findAllByIdAndStatus(Long id, Integer status);
}
