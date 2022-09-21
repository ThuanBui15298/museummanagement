package com.example.museummanagement.controller;

import com.example.museummanagement.dto.CulturalKnowledgeDTO;
import com.example.museummanagement.entity.CulturalKnowledge;
import com.example.museummanagement.service.CulturalKnowledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cultural-knowledge")
@RequiredArgsConstructor
public class CulturalKnowledgeApi {

    private final CulturalKnowledgeService culturalKnowledgeService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCulturalKnowledge(
            @RequestBody CulturalKnowledgeDTO culturalKnowledgeDTO) {
        culturalKnowledgeService.createCulturalKnowledge(culturalKnowledgeDTO);
        return new ResponseEntity<>(culturalKnowledgeDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCulturalKnowledge(
            @RequestBody CulturalKnowledgeDTO culturalKnowledgeDTO,
            @PathVariable("id") Long id) {
        culturalKnowledgeService.updateCulturalKnowledge(culturalKnowledgeDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCulturalKnowledge(@PathVariable("id") Long id) {
        culturalKnowledgeService.deleteCulturalKnowledge(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<CulturalKnowledge>> findAllCulturalKnowledge(Pageable pageable,
                                                                            @RequestBody CulturalKnowledgeDTO culturalKnowledgeDTO) {
        return new ResponseEntity<>(culturalKnowledgeService.findAllCulturalKnowledge(pageable, culturalKnowledgeDTO), HttpStatus.OK);
    }
}
