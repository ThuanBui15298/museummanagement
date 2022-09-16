package com.example.museummanagement.controller;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.UpcomingEventsDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.UpcomingEvents;
import com.example.museummanagement.service.FeaturedNewsService;
import com.example.museummanagement.service.UpcomingEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upcomingEvents")
@RequiredArgsConstructor
public class UpcomingEventsApi {

    private final UpcomingEventsService upcomingEventsService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUpcomingEvents(
            @RequestBody UpcomingEventsDTO upcomingEventsDTO) throws Exception {
        upcomingEventsService.createUpcomingEvents(upcomingEventsDTO);
        return new ResponseEntity(upcomingEventsDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateUpcomingEvents(
            @RequestBody UpcomingEventsDTO upcomingEventsDTO,
            @PathVariable("id") Long id) {
        upcomingEventsService.updateUpcomingEvents(upcomingEventsDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUpcomingEvents(@PathVariable("id") Long id) {
        upcomingEventsService.deleteUpcomingEvents(id);
        return new ResponseEntity<>("Deleted oke!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<UpcomingEvents>> getAllUpcomingEvents() {
        return new ResponseEntity<>(upcomingEventsService.findAll(), HttpStatus.OK);
    }
}
