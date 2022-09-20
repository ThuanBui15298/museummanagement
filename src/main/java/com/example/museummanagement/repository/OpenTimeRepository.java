package com.example.museummanagement.repository;

import com.example.museummanagement.entity.OpenTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface OpenTimeRepository extends JpaRepository<OpenTime, Long> {
    Optional<OpenTime> findByName(String name);

    List<OpenTime> findAllByIdAndStatus(Long id, Integer status);
}
