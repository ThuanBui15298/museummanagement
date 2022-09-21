package com.example.museummanagement.service;

import com.example.museummanagement.dto.ProfessionalFunctionDTO;
import com.example.museummanagement.entity.ProfessionalFunction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ProfessionalFunctionService {

    @Transactional
    ProfessionalFunctionDTO createProfessionalFunction(ProfessionalFunctionDTO professionalFunctionDTO);

    @Transactional
    ProfessionalFunctionDTO updateProfessionalFunction(ProfessionalFunctionDTO professionalFunctionDTO, Long id);

    void deleteProfessionalFunction(Long id);

    List<ProfessionalFunction> getAllProfessionalFunction();
}
