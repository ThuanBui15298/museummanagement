package com.example.museummanagement.service;


import com.example.museummanagement.dto.UpcomingEventsDTO;
import com.example.museummanagement.entity.UpcomingEvents;

import javax.transaction.Transactional;

public interface UpcomingEventsService {

    @Transactional
    UpcomingEventsDTO createUpcomingEvents(UpcomingEventsDTO upcomingEventsDTO) throws Exception;

    @Transactional
    UpcomingEventsDTO updateUpcomingEvents(UpcomingEventsDTO upcomingEventsDTO, Long id)  ;

    void deleteUpcomingEvents(Long id) ;

    Iterable<UpcomingEvents> findAll();
}
