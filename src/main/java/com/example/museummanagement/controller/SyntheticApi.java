package com.example.museummanagement.controller;

import com.example.museummanagement.entity.Synthetic;
import com.example.museummanagement.service.SyntheticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/synthetic")
public class SyntheticApi {


    @Autowired
    private SyntheticService syntheticService;

    @PostMapping("/create")
    public ResponseEntity<?> createSynthetic(@RequestBody Synthetic synthetic) {
        syntheticService.createSynthetic(synthetic);
        return new ResponseEntity<>(synthetic, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSynthetic(@RequestBody Synthetic synthetic, @PathVariable("id") Long id) {
        syntheticService.updateSynthetic(synthetic, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteSynthetic(@PathVariable("id") Long id) {
        syntheticService.deleteSynthetic(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @GetMapping("/get-all-synthetic")
    public ResponseEntity<List<Synthetic>> getAllSynthetic() {
        return new ResponseEntity<>(syntheticService.getAllSynthetic(),HttpStatus.OK);
    }
}
