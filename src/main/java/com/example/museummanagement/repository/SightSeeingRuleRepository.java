package com.example.museummanagement.repository;

import com.example.museummanagement.entity.SightSeeingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface SightSeeingRuleRepository extends JpaRepository<SightSeeingRule, Long> {
    Optional<SightSeeingRule> findByName(String name);

    List<SightSeeingRule> findAllByIdAndStatus(Long id, Integer status);
}
