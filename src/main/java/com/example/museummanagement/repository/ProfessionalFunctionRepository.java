package com.example.museummanagement.repository;

import com.example.museummanagement.entity.ProfessionalFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ProfessionalFunctionRepository extends JpaRepository<ProfessionalFunction, Long> {
    Optional<ProfessionalFunction> findByName(String name);

    List<ProfessionalFunction> findAllByIdAndStatus(Long id, Integer status);
}
