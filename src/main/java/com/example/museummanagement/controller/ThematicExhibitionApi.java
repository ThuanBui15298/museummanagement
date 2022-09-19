package com.example.museummanagement.controller;

import com.example.museummanagement.dto.ThematicExhibitionDTO;
import com.example.museummanagement.entity.ThematicExhibition;
import com.example.museummanagement.service.ThematicExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thematic-exhibitionApi")
@RequiredArgsConstructor
public class ThematicExhibitionApi {

    private final ThematicExhibitionService thematicExhibitionService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createThematicExhibition(
            @RequestBody ThematicExhibitionDTO thematicExhibitionDTO) {
        thematicExhibitionService.createThematicExhibition(thematicExhibitionDTO);
        return new ResponseEntity(thematicExhibitionDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateThematicExhibition(
            @RequestBody ThematicExhibitionDTO thematicExhibitionDTO,
            @PathVariable("id") Long id) {
        thematicExhibitionService.updateThematicExhibition(thematicExhibitionDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteThematicExhibition(@PathVariable("id") Long id) {
        thematicExhibitionService.deleteThematicExhibition(id);
        return new ResponseEntity<>("Deleted oke!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<ThematicExhibition>> getAllThematicExhibition() {
        return new ResponseEntity<>(thematicExhibitionService.findAll(), HttpStatus.OK);
    }
}
