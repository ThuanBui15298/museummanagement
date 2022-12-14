package com.example.museummanagement.controller;

import com.example.museummanagement.dto.ThematicExhibitionDTO;
import com.example.museummanagement.entity.ThematicExhibition;
import com.example.museummanagement.service.ThematicExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thematic-exhibition")
@RequiredArgsConstructor
public class ThematicExhibitionApi {

    private final ThematicExhibitionService thematicExhibitionService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createThematicExhibition(
            @RequestBody ThematicExhibitionDTO thematicExhibitionDTO) {
        thematicExhibitionService.createThematicExhibition(thematicExhibitionDTO);
        return new ResponseEntity<>(thematicExhibitionDTO, HttpStatus.OK);
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
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<ThematicExhibition>> getAllThematicExhibition(Pageable pageable,
                                                                                 @RequestBody ThematicExhibitionDTO thematicExhibitionDTO) {
        return new ResponseEntity<>(thematicExhibitionService.findAll(pageable, thematicExhibitionDTO), HttpStatus.OK);
    }
}
