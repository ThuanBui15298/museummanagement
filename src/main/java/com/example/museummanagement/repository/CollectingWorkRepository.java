package com.example.museummanagement.repository;

import com.example.museummanagement.entity.CollectingWork;
import com.example.museummanagement.entity.FeaturedNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectingWorkRepository extends JpaRepository<CollectingWork, Long> {

    Optional<CollectingWork> findByName(String name);

    List<CollectingWork> findAllByIdAndStatus(Long id, Integer status);
}
