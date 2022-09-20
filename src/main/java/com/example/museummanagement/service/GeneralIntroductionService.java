package com.example.museummanagement.service;

import com.example.museummanagement.dto.GeneralIntroductionDTO;
import com.example.museummanagement.entity.GeneralIntroduction;

import javax.transaction.Transactional;
import java.util.List;

public interface GeneralIntroductionService {

    @Transactional
    GeneralIntroductionDTO createGeneralIntroduction(GeneralIntroductionDTO generalIntroductionDTO);

    @Transactional
    GeneralIntroductionDTO updateGeneralIntroduction(GeneralIntroductionDTO generalIntroductionDTO, Long id)  ;

    void deleteGeneralIntroduction(Long id) ;

    List<GeneralIntroduction> findAllGeneralIntroduction();
}
