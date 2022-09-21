package com.example.museummanagement.controller;


import com.example.museummanagement.dto.CollectionsDTO;
import com.example.museummanagement.entity.Collections;
import com.example.museummanagement.service.CollectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return new ResponseEntity<>(collectionsDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCollections(
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
    public ResponseEntity<Page<Collections>> findAllCollectingWork(Pageable pageable,
                                                                   @RequestBody CollectionsDTO collectionsDTO) {
        return new ResponseEntity<>(collectionsService.findAllCollections(pageable, collectionsDTO), HttpStatus.OK);
    }


}
