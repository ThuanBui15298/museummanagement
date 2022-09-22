package com.example.museummanagement.repository;

import com.example.museummanagement.dto.ItinerantDisplayDTO;
import com.example.museummanagement.entity.ItinerantDisplay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ItinerantDisplayRepository extends JpaRepository<ItinerantDisplay, Long> {
    Optional<ItinerantDisplay> findByName(String name);

    List<ItinerantDisplay> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.type = 4 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<ItinerantDisplay> findAllBySearch(Pageable pageable, @Param("search") String search);
}
