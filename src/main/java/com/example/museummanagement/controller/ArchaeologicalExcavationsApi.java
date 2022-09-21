package com.example.museummanagement.controller;

import com.example.museummanagement.dto.ArchaeologicalExcavationsDTO;
import com.example.museummanagement.entity.ArchaeologicalExcavations;
import com.example.museummanagement.service.ArchaeologicalExcavationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/archaeological-excavation")
public class ArchaeologicalExcavationsApi {

    @Autowired
    private ArchaeologicalExcavationsService archaeologicalExcavationsService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createArchaeologicalExcavations(
            @RequestBody ArchaeologicalExcavationsDTO archaeologicalExcavationsDTO) {
        archaeologicalExcavationsService.createArchaeologicalExcavations(archaeologicalExcavationsDTO);
        return new ResponseEntity<>(archaeologicalExcavationsDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateArchaeologicalExcavations(
            @RequestBody ArchaeologicalExcavationsDTO archaeologicalExcavationsDTO,
            @PathVariable("id") Long id) {
        archaeologicalExcavationsService.updateArchaeologicalExcavations(archaeologicalExcavationsDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteArchaeologicalExcavations(@PathVariable("id") Long id) {
        archaeologicalExcavationsService.deleteArchaeologicalExcavations(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<ArchaeologicalExcavations>> getAllThematicExhibition(Pageable pageable,
                                                                                    @RequestBody ArchaeologicalExcavationsDTO archaeologicalExcavationsDTO) {
        return new ResponseEntity<>(archaeologicalExcavationsService.findAllArchaeologicalExcavations(pageable, archaeologicalExcavationsDTO), HttpStatus.OK);
    }
}

