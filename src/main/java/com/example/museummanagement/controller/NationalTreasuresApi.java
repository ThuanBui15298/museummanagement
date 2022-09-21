package com.example.museummanagement.controller;

import com.example.museummanagement.dto.NationalTreasuresDTO;
import com.example.museummanagement.entity.NationalTreasures;
import com.example.museummanagement.service.NationalTreasuresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/national-treasures")
@RequiredArgsConstructor
public class NationalTreasuresApi {

    private final NationalTreasuresService nationalTreasuresService;

    @PostMapping("/create")
    public ResponseEntity<?> createNationalTreasures(@RequestBody NationalTreasuresDTO nationalTreasuresDTO) {
        nationalTreasuresService.createNationalTreasure(nationalTreasuresDTO);
        return new ResponseEntity<>(nationalTreasuresDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> upadteNationalTreasures(@RequestBody NationalTreasuresDTO nationalTreasuresDTO,
                                                     @PathVariable("id") Long id) {
        nationalTreasuresService.updateNationalTreasure(nationalTreasuresDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteNationalTreasures(@PathVariable("id") Long id) {
        nationalTreasuresService.deleteNationalTreasure(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<NationalTreasures>> getAllNationalTreasures() {
        return new ResponseEntity<>(nationalTreasuresService.getAllNationalTreasure(), HttpStatus.OK);
    }
}
