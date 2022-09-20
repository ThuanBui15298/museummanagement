package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstructionRepository  extends JpaRepository<Instruction, Long> {

    Optional<Instruction> findByName(String name);

    List<Instruction> findAllByIdAndStatus(Long id, Integer status);
}
