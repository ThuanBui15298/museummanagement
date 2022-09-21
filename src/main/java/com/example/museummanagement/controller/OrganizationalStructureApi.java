package com.example.museummanagement.controller;

import com.example.museummanagement.dto.OrganizationalStructureDTO;
import com.example.museummanagement.entity.OrganizationalStructure;
import com.example.museummanagement.service.OrganizationalStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizational-structure")
@RequiredArgsConstructor
public class OrganizationalStructureApi {

    private final OrganizationalStructureService  organizationalStructureService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrganizationalStructure(@RequestBody OrganizationalStructureDTO organizationalStructureDTO) {
        organizationalStructureService.createOrganizationalStructure(organizationalStructureDTO);
        return new ResponseEntity<>(organizationalStructureDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrganizationalStructure(@RequestBody OrganizationalStructureDTO organizationalStructureDTO,
                                                     @PathVariable("id") Long id) {
        organizationalStructureService.updateOrganizationalStructure(organizationalStructureDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrganizationalStructure(@PathVariable("id") Long id) {
        organizationalStructureService.deleteOrganizationalStructure(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<OrganizationalStructure>> getAllOrganizationalStructure() {
        return new ResponseEntity<>(organizationalStructureService.getAllOrganizationalStructure(), HttpStatus.OK);
    }


}
