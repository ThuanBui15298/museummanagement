package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Synthetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface SyntheticRepository extends JpaRepository<Synthetic, Long> {
    Optional<Synthetic> findByName(String name);

    List<Synthetic> findALlByIdAndStatus(Long id, Integer status);
}
