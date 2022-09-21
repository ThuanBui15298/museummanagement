package com.example.museummanagement.repository;

import com.example.museummanagement.entity.EducationalActivities;
import com.example.museummanagement.entity.GeneralIntroduction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EducationalActivitiesRepository extends JpaRepository<EducationalActivities,Long> {

    Optional<EducationalActivities> findByName(String name);

    List<EducationalActivities> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.type = 12 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<EducationalActivities> findAllBySearch(Pageable pageable, @Param("search") String search);

}
