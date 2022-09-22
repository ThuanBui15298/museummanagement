package com.example.museummanagement.repository;

import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.UpcomingEvents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UpcomingEventsRepository extends JpaRepository<UpcomingEvents, Long> {

    Optional<UpcomingEvents> findByName(String name);

    List<UpcomingEvents> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.type = 1 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<UpcomingEvents> findAllBySearch(Pageable pageable, @Param("search") String search);
}
