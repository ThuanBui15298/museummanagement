package com.example.museummanagement.service;

import com.example.museummanagement.dto.EducationalActivitiesDTO;
import com.example.museummanagement.entity.EducationalActivities;

import javax.transaction.Transactional;
import java.util.List;

public interface EducationalActivitiesService {

    @Transactional
    EducationalActivitiesDTO createEducationalActivities(EducationalActivitiesDTO educationalActivitiesDTO);

    @Transactional
    EducationalActivitiesDTO updateEducationalActivities(EducationalActivitiesDTO educationalActivitiesDTO, Long id)  ;

    void deleteEducationalActivities(Long id) ;

    List<EducationalActivities> findAllEducationalActivities();

}
