package com.example.museummanagement.controller;

import com.example.museummanagement.dto.CollectingWorkDTO;
import com.example.museummanagement.entity.CollectingWork;
import com.example.museummanagement.service.CollectingWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collecting-work")
@RequiredArgsConstructor
public class CollectingWorkApi {

    private final CollectingWorkService collectingWorkService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCollectingWork(
            @RequestBody CollectingWorkDTO collectingWorkDTO) {
        collectingWorkService.createCollectingWork(collectingWorkDTO);
        return new ResponseEntity(collectingWorkDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCollectingWork(
            @RequestBody CollectingWorkDTO collectingWorkDTO,
            @PathVariable("id") Long id) {
        collectingWorkService.updateCollectingWork(collectingWorkDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCollectingWork(@PathVariable("id") Long id) {
        collectingWorkService.deleteCollectingWork(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<CollectingWork>> findAllCollectingWork() {
        return new ResponseEntity<>(collectingWorkService.findAllCollectingWork(), HttpStatus.OK);
    }
}
