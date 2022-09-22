package com.example.museummanagement.controller;

import com.example.museummanagement.dto.UpcomingEventsDTO;
import com.example.museummanagement.entity.UpcomingEvents;
import com.example.museummanagement.service.UpcomingEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upcoming-events")
@RequiredArgsConstructor
public class UpcomingEventsApi {

    private final UpcomingEventsService upcomingEventsService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUpcomingEvents(
            @RequestBody UpcomingEventsDTO upcomingEventsDTO) throws Exception {
        upcomingEventsService.createUpcomingEvents(upcomingEventsDTO);
        return new ResponseEntity<>(upcomingEventsDTO, HttpStatus.OK);
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
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<UpcomingEvents>> getAllUpcomingEvents(Pageable pageable,
                                                                     @RequestBody UpcomingEventsDTO upcomingEventsDTO) {
        return new ResponseEntity<>(upcomingEventsService.findAllUpcomingEvents(pageable, upcomingEventsDTO), HttpStatus.OK);
    }
}
