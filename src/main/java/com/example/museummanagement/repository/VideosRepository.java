package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface VideosRepository extends JpaRepository<Videos, Long> {

    Optional<Videos> findByName(String name);

    List<Videos> findAllByIdAndStatus(Long id, Integer status);

}
