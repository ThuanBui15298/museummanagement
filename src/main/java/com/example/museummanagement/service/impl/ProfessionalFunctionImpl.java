package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.ProfessionalFunctionDTO;
import com.example.museummanagement.entity.ProfessionalFunction;
import com.example.museummanagement.repository.ProfessionalFunctionRepository;
import com.example.museummanagement.service.ProfessionalFunctionService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessionalFunctionImpl implements ProfessionalFunctionService {
    private final ProfessionalFunctionRepository professionalFunctionRepository;

    @SneakyThrows
    @Transactional
    @Override
    public ProfessionalFunctionDTO createProfessionalFunction(ProfessionalFunctionDTO professionalFunctionDTO) {
        Optional<ProfessionalFunction> optionalProfessionalFunction = professionalFunctionRepository.findByName(professionalFunctionDTO.getName());
        ProfessionalFunction professionalFunction = new ProfessionalFunction();
        if (optionalProfessionalFunction.isEmpty()) {
            professionalFunction.setName(professionalFunctionDTO.getName());
            professionalFunction.setTitle(professionalFunctionDTO.getTitle());
            professionalFunction.setContent(professionalFunctionDTO.getContent());
            professionalFunction.setType(Constants.TYPE_PROFESSIONAL_FUNCTION);
            professionalFunction.setStatus(Constants.STATUS_ACTIVE);
            professionalFunctionRepository.save(professionalFunction);
        } else {
            throw new Exception("Name existed!");
        }
        return professionalFunctionDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public ProfessionalFunctionDTO updateProfessionalFunction(ProfessionalFunctionDTO professionalFunctionDTO, Long id) {
        Optional<ProfessionalFunction> optionalProfessionalFunction = professionalFunctionRepository.findById(id);
        if (optionalProfessionalFunction.isPresent()) {
            ProfessionalFunction professionalFunction = optionalProfessionalFunction.get();
            Optional<ProfessionalFunction> optional = professionalFunctionRepository.findByName(professionalFunctionDTO.getName());
            if (optional.isEmpty() || professionalFunction.getId().equals(optional.get().getId())) {
                professionalFunction.setName(professionalFunctionDTO.getName());
                professionalFunction.setTitle(professionalFunctionDTO.getTitle());
                professionalFunction.setContent(professionalFunctionDTO.getContent());
                professionalFunction.setType(Constants.TYPE_PROFESSIONAL_FUNCTION);
                professionalFunction.setStatus(Constants.STATUS_ACTIVE);
                professionalFunctionRepository.save(professionalFunction);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found by id: " + id);
        }
        return professionalFunctionDTO;
    }

    @SneakyThrows
    @Override
    public void deleteProfessionalFunction(Long id) {
        List<ProfessionalFunction> professionalFunctionsList = professionalFunctionRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(professionalFunctionsList)) {
            throw new Exception("Can not found!");
        }
        for (ProfessionalFunction professionalFunction : professionalFunctionsList) {
            professionalFunction.setStatus(Constants.STATUS_INACTIVE);
            professionalFunctionRepository.save(professionalFunction);
        }
    }

    @Override
    public List<ProfessionalFunction> getAllProfessionalFunction() {
        return professionalFunctionRepository.findAll();
    }
}
