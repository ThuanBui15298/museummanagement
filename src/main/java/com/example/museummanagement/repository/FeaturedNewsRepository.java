package com.example.museummanagement.repository;

import com.example.museummanagement.entity.FeaturedNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeaturedNewsRepository extends JpaRepository<FeaturedNews, Long> {

    Optional<FeaturedNews> findByName(String name);

    List<FeaturedNews> findAllByIdAndStatus(Long id, Integer status);

}
