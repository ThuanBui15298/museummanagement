package com.example.museummanagement.service;

import com.example.museummanagement.dto.ItinerantDisplayDTO;
import com.example.museummanagement.entity.ItinerantDisplay;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItinerantDisplayService {

    ItinerantDisplayDTO createItinerantDisplay(ItinerantDisplayDTO itinerantDisplayDTO);

    ItinerantDisplayDTO updateItinerantDisplay(Long id, ItinerantDisplayDTO itinerantDisplayDTO);

    void deleteItinerantDisplay(Long id);

    List<ItinerantDisplay> getAllItinerantDisplay();

}
