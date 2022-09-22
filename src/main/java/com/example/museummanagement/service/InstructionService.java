package com.example.museummanagement.service;



import com.example.museummanagement.dto.InstructionDTO;
import com.example.museummanagement.entity.Instruction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface InstructionService {


    @Transactional
    InstructionDTO createInstruction(InstructionDTO instructionDTO);

    @Transactional
    InstructionDTO updateInstruction(InstructionDTO instructionDTO, Long id)  ;

    void deleteInstruction(Long id) ;

    Page<Instruction> findAllInstruction(Pageable pageable, InstructionDTO instructionDTO);
}
