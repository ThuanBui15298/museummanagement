package com.example.museummanagement.repository;

import com.example.museummanagement.entity.EducationalActivities;
import com.example.museummanagement.entity.GeneralIntroduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationalActivitiesRepository extends JpaRepository<EducationalActivities,Long> {

    Optional<EducationalActivities> findByName(String name);

    List<EducationalActivities> findAllByIdAndStatus(Long id, Integer status);

}
