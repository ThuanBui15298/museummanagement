package com.example.museummanagement.controller;

import com.example.museummanagement.dto.SightSeeingRuleDTO;
import com.example.museummanagement.entity.SightSeeingRule;
import com.example.museummanagement.service.SightSeeingRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("sightseeing-rule")
public class SightSeeingRuleApi {

    @Autowired
    private SightSeeingRuleService sightSeeingRuleService;

    @PostMapping("/create")
    public ResponseEntity<?> createSightSeeingRule(@RequestBody SightSeeingRuleDTO sightSeeingRuleDTO) {
        sightSeeingRuleService.createSightSeeingRule(sightSeeingRuleDTO);
        return new ResponseEntity<>(sightSeeingRuleDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSightSeeingRule(@RequestBody SightSeeingRuleDTO sightSeeingRuleDTO,
                                                   @PathVariable("id") Long id) {
        sightSeeingRuleService.updateSightSeeingRule(sightSeeingRuleDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteSightSeeingRule(@PathVariable("id") Long id) {
        sightSeeingRuleService.deleteSightSeeingRule(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Page<SightSeeingRule>> getAllSightSeeingRule(Pageable pageable,
                                                                       @RequestBody SightSeeingRuleDTO sightSeeingRuleDTO) {
        return new ResponseEntity<>(sightSeeingRuleService.findAllSightSeeingRule(pageable, sightSeeingRuleDTO), HttpStatus.OK);
    }
}
