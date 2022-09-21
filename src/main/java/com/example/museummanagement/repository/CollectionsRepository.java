package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Collections;
import com.example.museummanagement.entity.ItinerantDisplay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CollectionsRepository extends JpaRepository<Collections, Long> {

    Optional<Collections> findByName(String name);

    List<Collections> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.type = 11 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<Collections> findAllBySearch(Pageable pageable, @Param("search") String search);
}
