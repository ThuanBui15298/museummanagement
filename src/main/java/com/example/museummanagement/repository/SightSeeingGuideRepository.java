package com.example.museummanagement.repository;

import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.SightSeeingGuide;
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
public interface SightSeeingGuideRepository extends JpaRepository<SightSeeingGuide, Long>{
    Optional<SightSeeingGuide> findByName(String name);

    List<SightSeeingGuide> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.type = 9 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<SightSeeingGuide> findAllBySearch(Pageable pageable, @Param("search") String search);
}

