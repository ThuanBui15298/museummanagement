package com.example.museummanagement.controller;

import com.example.museummanagement.dto.ShowRegularlyDTO;
import com.example.museummanagement.entity.ShowRegularly;
import com.example.museummanagement.service.ShowRegularlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show-regularly")
public class ShowRegularlyApi {

    @Autowired
    private ShowRegularlyService showRegularlyService;

    @PostMapping("/create")
    public ResponseEntity<?> crerateShowRegularly(@RequestBody ShowRegularlyDTO showRegularlyDTO) {
        showRegularlyService.createShowRegularly(showRegularlyDTO);
        return new ResponseEntity<>(showRegularlyDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShowRegularly(@RequestBody ShowRegularlyDTO showRegularlyDTO, @PathVariable("id") Long id) {
        showRegularlyService.updateShowRegularly(showRegularlyDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteShowRegularly(@PathVariable("id") Long id) {
        showRegularlyService.deleteShowRegularly(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<ShowRegularly>> getAllShowRegularly(Pageable pageable,
                                                                   @RequestBody ShowRegularlyDTO showRegularlyDTO) {
        return new ResponseEntity<>(showRegularlyService.findAllShowRegularly(pageable, showRegularlyDTO),HttpStatus.OK);
    }

}
