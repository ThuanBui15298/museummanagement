package com.example.museummanagement.controller;

import com.example.museummanagement.entity.TypicalArtifacts;
import com.example.museummanagement.service.TypicalArtifactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typical-artifacts")
@RequiredArgsConstructor
public class TypicalArtifactsApi {
    @Autowired
    private TypicalArtifactsService typicalArtifactsService;

    @PostMapping("/create")
    public ResponseEntity<?> createTypicalArtifact(@RequestBody TypicalArtifacts typicalArtifacts) {
        typicalArtifactsService.createTypicalArtifact(typicalArtifacts);
        return new ResponseEntity<>(typicalArtifacts, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTypicalArtifact(@RequestBody TypicalArtifacts typicalArtifacts,
                                                   @PathVariable("id") Long id) {
        typicalArtifactsService.updateTypicalArtifact(typicalArtifacts, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteTypicalArtifact(@PathVariable("id") Long id) {
        typicalArtifactsService.deleteTypicalArtifact(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TypicalArtifacts>> getAllTypicalArtifacts() {
        return new ResponseEntity<>(typicalArtifactsService.getAllTypicalArtifacts(), HttpStatus.OK);
    }
}