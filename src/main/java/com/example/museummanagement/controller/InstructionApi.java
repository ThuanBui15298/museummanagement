package com.example.museummanagement.controller;

import com.example.museummanagement.dto.InstructionDTO;
import com.example.museummanagement.entity.Instruction;
import com.example.museummanagement.service.InstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instruction")
@RequiredArgsConstructor
public class InstructionApi {
    private final InstructionService instructionService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createInstruction(
            @RequestBody InstructionDTO instructionDTO) {
        instructionService.createInstruction(instructionDTO);
        return new ResponseEntity<>(instructionDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateInstruction(
            @RequestBody InstructionDTO instructionDTO,
            @PathVariable("id") Long id) {
        instructionService.updateInstruction(instructionDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteInstruction(@PathVariable("id") Long id) {
        instructionService.deleteInstruction(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Instruction>> findAllInstruction(Pageable pageable,
                                                                @RequestBody InstructionDTO instructionDTO) {
        return new ResponseEntity<>(instructionService.findAllInstruction(pageable, instructionDTO), HttpStatus.OK);
    }
}
