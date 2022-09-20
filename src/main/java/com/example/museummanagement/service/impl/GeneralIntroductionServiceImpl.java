package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.GeneralIntroductionDTO;
import com.example.museummanagement.dto.InstructionDTO;
import com.example.museummanagement.entity.GeneralIntroduction;
import com.example.museummanagement.entity.Instruction;
import com.example.museummanagement.repository.GeneralIntroductionRepository;
import com.example.museummanagement.repository.InstructionRepository;
import com.example.museummanagement.service.GeneralIntroductionService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralIntroductionServiceImpl implements GeneralIntroductionService {

    private final GeneralIntroductionRepository generalIntroductionRepository;

    @SneakyThrows
    @Transactional
    @Override
    public GeneralIntroductionDTO createGeneralIntroduction(GeneralIntroductionDTO generalIntroductionDTO) {
        Optional<GeneralIntroduction> optionalGeneralIntroduction = generalIntroductionRepository.findByName(generalIntroductionDTO.getName());
        GeneralIntroduction generalIntroduction = new GeneralIntroduction();
        if (optionalGeneralIntroduction.isEmpty()) {
            generalIntroduction.setType(Constants.TYPE_GENERAL_INTRODUCTION);
            generalIntroduction.setName(generalIntroductionDTO.getName());
            generalIntroduction.setTitle(generalIntroductionDTO.getTitle());
            generalIntroduction.setContent(generalIntroductionDTO.getContent());
            generalIntroduction.setStatus(Constants.STATUS_ACTIVE);
            generalIntroductionRepository.save(generalIntroduction);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return generalIntroductionDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public GeneralIntroductionDTO updateGeneralIntroduction(GeneralIntroductionDTO generalIntroductionDTO, Long id) {
        Optional<GeneralIntroduction> optionalGeneralIntroduction = generalIntroductionRepository.findById(id);
        if (optionalGeneralIntroduction.isPresent()) {
            GeneralIntroduction generalIntroduction = optionalGeneralIntroduction.get();
            Optional<GeneralIntroduction> generalIntroductionOpt = generalIntroductionRepository.findByName(generalIntroductionDTO.getName());
            if (generalIntroductionOpt.isEmpty() || generalIntroduction.getId().equals(generalIntroductionOpt.get().getId())) {
                generalIntroduction.setType(Constants.TYPE_GENERAL_INTRODUCTION);
                generalIntroduction.setName(generalIntroductionDTO.getName());
                generalIntroduction.setTitle(generalIntroductionDTO.getTitle());
                generalIntroduction.setContent(generalIntroductionDTO.getContent());
                generalIntroduction.setStatus(Constants.STATUS_ACTIVE);
                generalIntroductionRepository.save(generalIntroduction);
            } else {
                throw new MessageDescriptorFormatException("Name existed!");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found id: " + id);
        }
        return generalIntroductionDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteGeneralIntroduction(Long id) {
        List<GeneralIntroduction> generalIntroductionsList = generalIntroductionRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(generalIntroductionsList)) {
            throw new MessageDescriptorFormatException("No existed");
        }
        for (GeneralIntroduction generalIntroduction : generalIntroductionsList) {
            generalIntroduction.setStatus(Constants.STATUS_INACTIVE);
            generalIntroduction.setModifiedDate(new Date());
            generalIntroductionRepository.save(generalIntroduction);
        }
    }

    @Override
    public List<GeneralIntroduction> findAllGeneralIntroduction() {
        return generalIntroductionRepository.findAll();
    }
}
