package com.example.museummanagement.controller;

import com.example.museummanagement.dto.ItinerantDisplayDTO;
import com.example.museummanagement.entity.ItinerantDisplay;
import com.example.museummanagement.service.ItinerantDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itinerant-display")
public class ItinerantDisplayApi {

    @Autowired
    private ItinerantDisplayService itinerantDisplayService;

    @PostMapping("/create")
    public ResponseEntity<?> createItinerantDisplay(@RequestBody ItinerantDisplayDTO itinerantDisplayDTO) {
        itinerantDisplayService.createItinerantDisplay(itinerantDisplayDTO);
        return new ResponseEntity<>(itinerantDisplayDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateItinerantDisplay(@RequestBody ItinerantDisplayDTO itinerantDisplayDTO,
                                                                      @PathVariable("id") Long id){
        itinerantDisplayService.updateItinerantDisplay(id, itinerantDisplayDTO);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteItinerantDisplay(@PathVariable("id") Long id) {
        itinerantDisplayService.deleteItinerantDisplay(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public List<ItinerantDisplay> getAllItinerantDisplay() {
        return itinerantDisplayService.getAllItinerantDisplay();
    }
}
