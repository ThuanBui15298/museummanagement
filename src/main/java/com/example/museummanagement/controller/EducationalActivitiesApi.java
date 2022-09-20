package com.example.museummanagement.controller;

import com.example.museummanagement.dto.EducationalActivitiesDTO;
import com.example.museummanagement.entity.EducationalActivities;
import com.example.museummanagement.service.EducationalActivitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/educational-activities")
@RequiredArgsConstructor
public class EducationalActivitiesApi {

        private final EducationalActivitiesService educationalActivitiesService;

        @PostMapping(value = "/create")
        public ResponseEntity<?> createEducationalActivities(
                @RequestBody EducationalActivitiesDTO educationalActivitiesDTO) {
            educationalActivitiesService.createEducationalActivities(educationalActivitiesDTO);
            return new ResponseEntity(educationalActivitiesDTO, HttpStatus.OK);
        }

        @PutMapping(value = "/update/{id}")
        public ResponseEntity<?> updateEducationalActivities(
                @RequestBody EducationalActivitiesDTO educationalActivitiesDTO,
                @PathVariable("id") Long id) {
            educationalActivitiesService.updateEducationalActivities(educationalActivitiesDTO, id);
            return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
        }

        @PutMapping(value = "/delete/{id}")
        public ResponseEntity<?> deleteEducationalActivities(@PathVariable("id") Long id) {
            educationalActivitiesService.deleteEducationalActivities(id);
            return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
        }

        @GetMapping("/get-all")
        public ResponseEntity<Iterable<EducationalActivities>> findAllEducationalActivities() {
            return new ResponseEntity<>(educationalActivitiesService.findAllEducationalActivities(), HttpStatus.OK);
        }

    }
