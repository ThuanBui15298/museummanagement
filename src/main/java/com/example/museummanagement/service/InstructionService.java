package com.example.museummanagement.service;



import com.example.museummanagement.dto.InstructionDTO;
import com.example.museummanagement.entity.Instruction;

import javax.transaction.Transactional;
import java.util.List;

public interface InstructionService {


    @Transactional
    InstructionDTO createInstruction(InstructionDTO instructionDTO);

    @Transactional
    InstructionDTO updateInstruction(InstructionDTO instructionDTO, Long id)  ;

    void deleteInstruction(Long id) ;

    List<Instruction> findAllInstruction();
}
