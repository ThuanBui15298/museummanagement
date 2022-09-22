package com.example.museummanagement.service;


import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.UpcomingEventsDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.UpcomingEvents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public interface UpcomingEventsService {

    @Transactional
    UpcomingEventsDTO createUpcomingEvents(UpcomingEventsDTO upcomingEventsDTO);

    @Transactional
    UpcomingEventsDTO updateUpcomingEvents(UpcomingEventsDTO upcomingEventsDTO, Long id)  ;

    void deleteUpcomingEvents(Long id) ;

    Page<UpcomingEvents> findAllUpcomingEvents(Pageable pageable, UpcomingEventsDTO upcomingEventsDTO);

}
