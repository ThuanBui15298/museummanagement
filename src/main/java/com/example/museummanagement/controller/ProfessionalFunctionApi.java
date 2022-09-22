package com.example.museummanagement.controller;

import com.example.museummanagement.dto.ProfessionalFunctionDTO;
import com.example.museummanagement.entity.ProfessionalFunction;
import com.example.museummanagement.service.ProfessionalFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/professional-function")
@RequiredArgsConstructor
public class ProfessionalFunctionApi {

    private final ProfessionalFunctionService professionalFunctionService;

    @PostMapping("/create")
    public ResponseEntity<?> createProfessionalFunction(@RequestBody ProfessionalFunctionDTO professionalFunctionDTO) {
        professionalFunctionService.createProfessionalFunction(professionalFunctionDTO);
        return new ResponseEntity<>(professionalFunctionDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProfessionalFunction(@RequestBody ProfessionalFunctionDTO professionalFunctionDTO,
                                                        @PathVariable("id") Long id) {
        professionalFunctionService.updateProfessionalFunction(professionalFunctionDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfessionalFunction(@PathVariable("id") Long id) {
        professionalFunctionService.deleteProfessionalFunction(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<ProfessionalFunction>> getAllProfessionalFunction(Pageable pageable,
                                                                                 @RequestBody ProfessionalFunctionDTO professionalFunctionDTO) {
        return new ResponseEntity<>(professionalFunctionService.findAllProfessionalFunction(pageable, professionalFunctionDTO), HttpStatus.OK);
    }
}
