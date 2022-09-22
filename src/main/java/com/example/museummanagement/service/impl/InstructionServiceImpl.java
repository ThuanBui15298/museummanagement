package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.InstructionDTO;
import com.example.museummanagement.entity.Instruction;
import com.example.museummanagement.repository.InstructionRepository;
import com.example.museummanagement.service.InstructionService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructionServiceImpl implements InstructionService {
    private final InstructionRepository instructionRepository;

    @SneakyThrows
    @Transactional
    @Override
    public InstructionDTO createInstruction(InstructionDTO instructionDTO) {
        Optional<Instruction> optionalInstruction = instructionRepository.findByName(instructionDTO.getName());
        Instruction instruction = new Instruction();
        if (optionalInstruction.isEmpty()) {
            instruction.setType(Constants.TYPE_INSTRUCTION);
            instruction.setName(instructionDTO.getName());
            instruction.setTitle(instructionDTO.getTitle());
            instruction.setContent(instructionDTO.getContent());
            instruction.setStatus(Constants.STATUS_ACTIVE);
            instructionRepository.save(instruction);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return instructionDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public InstructionDTO updateInstruction(InstructionDTO instructionDTO, Long id) {
        Optional<Instruction> optionalInstruction = instructionRepository.findById(id);
        if (optionalInstruction.isPresent()) {
            Instruction instruction = optionalInstruction.get();
            Optional<Instruction> instructionOpt = instructionRepository.findByName(instructionDTO.getName());
            if (instructionOpt.isEmpty() || instruction.getId().equals(instructionOpt.get().getId())) {
                instruction.setType(Constants.TYPE_INSTRUCTION);
                instruction.setName(instructionDTO.getName());
                instruction.setTitle(instructionDTO.getTitle());
                instruction.setContent(instructionDTO.getContent());
                instruction.setStatus(Constants.STATUS_ACTIVE);
                instructionRepository.save(instruction);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found id: " + id);
        }
        return instructionDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteInstruction(Long id) {
        List<Instruction> instructions = instructionRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(instructions)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (Instruction instruction : instructions) {
            instruction.setStatus(Constants.STATUS_INACTIVE);
            instruction.setModifiedDate(new Date());
            instructionRepository.save(instruction);
        }
    }

    @Override
    public Page<Instruction> findAllInstruction(Pageable pageable, InstructionDTO instructionDTO) {
        String search;
        if (StringUtils.isEmpty(instructionDTO.getSearch())) {
            search = "%%";
        } else {
            search = "%" + instructionDTO.getSearch().toLowerCase() + "%";
        }
        return instructionRepository.findAllBySearch(pageable, search);
    }
}
