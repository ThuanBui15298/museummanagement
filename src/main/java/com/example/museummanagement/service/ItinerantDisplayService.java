package com.example.museummanagement.service;

import com.example.museummanagement.dto.ItinerantDisplayDTO;
import com.example.museummanagement.entity.ItinerantDisplay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItinerantDisplayService {

    ItinerantDisplayDTO createItinerantDisplay(ItinerantDisplayDTO itinerantDisplayDTO);

    ItinerantDisplayDTO updateItinerantDisplay(Long id, ItinerantDisplayDTO itinerantDisplayDTO);

    void deleteItinerantDisplay(Long id);

    Page<ItinerantDisplay> getAllItinerantDisplay(Pageable pageable, ItinerantDisplayDTO itinerantDisplayDTO);

}
