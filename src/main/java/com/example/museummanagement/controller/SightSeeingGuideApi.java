package com.example.museummanagement.controller;

import com.example.museummanagement.dto.SightSeeingGuideDTO;
import com.example.museummanagement.entity.SightSeeingGuide;
import com.example.museummanagement.service.SightSeeingGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sightseeing-guide")
public class SightSeeingGuideApi {

    @Autowired
    private SightSeeingGuideService sightSeeingGuideService;

    @PostMapping("/create")
    public ResponseEntity<?> createSightSeeingGuide(@RequestBody SightSeeingGuideDTO sightSeeingGuideDTO) {
        sightSeeingGuideService.createSightSeeingGuide(sightSeeingGuideDTO);
        return new ResponseEntity<>(sightSeeingGuideDTO, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateSightSeeingGuide(@RequestBody SightSeeingGuideDTO sightSeeingGuideDTO,
                                                    @PathVariable("id") Long id) {
        sightSeeingGuideService.updateSightSeeingGuide(sightSeeingGuideDTO, id);
        return new ResponseEntity<>("Update successfully!" , HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteSightSeeingGuide(@PathVariable("id") Long id) {
        sightSeeingGuideService.deleteSightSeeingGuide(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<SightSeeingGuide>> getAllSightSeeingGuide(Pageable pageable,
                                                                         @RequestBody SightSeeingGuideDTO sightSeeingGuideDTO) {
        return new ResponseEntity<>(sightSeeingGuideService.findAllSightSeeingGuide(pageable, sightSeeingGuideDTO), HttpStatus.OK);
    }
}
