package com.example.museummanagement.repository;

import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.UpcomingEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UpcomingEventsRepository extends JpaRepository<UpcomingEvents, Long> {

    Optional<UpcomingEvents> findByName(String name);

    List<UpcomingEvents> findAllByIdAndStatus(Long id, Integer status);
}
