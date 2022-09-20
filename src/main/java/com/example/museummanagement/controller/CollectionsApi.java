package com.example.museummanagement.controller;


import com.example.museummanagement.dto.CollectingWorkDTO;
import com.example.museummanagement.dto.CollectionsDTO;
import com.example.museummanagement.entity.CollectingWork;
import com.example.museummanagement.entity.Collections;
import com.example.museummanagement.service.CollectingWorkService;
import com.example.museummanagement.service.CollectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class CollectionsApi {

    private final CollectionsService collectionsService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCollections(
            @RequestBody CollectionsDTO collectionsDTO) {
        collectionsService.createCollections(collectionsDTO);
        return new ResponseEntity(collectionsDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCollectingWork(
            @RequestBody CollectionsDTO collectionsDTO,
            @PathVariable("id") Long id) {
        collectionsService.updateCollections(collectionsDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCollections(@PathVariable("id") Long id) {
        collectionsService.deleteCollections(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<Collections>> findAllCollectingWork() {
        return new ResponseEntity<>(collectionsService.findAllCollections(), HttpStatus.OK);
    }


}
