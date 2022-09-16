package com.example.museummanagement.repository;

import com.example.museummanagement.entity.ShowRegularly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ShowRegularlyRepository extends JpaRepository<ShowRegularly, Long> {
    Optional<ShowRegularly> findByName(String name);

    List<ShowRegularly> findAllByIdAndStatus(Long id, Integer status);
}
