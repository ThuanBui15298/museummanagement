package com.example.museummanagement.service;

import com.example.museummanagement.dto.ThematicExhibitionDTO;
import com.example.museummanagement.entity.ThematicExhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public interface ThematicExhibitionService {

    @Transactional
    ThematicExhibitionDTO createThematicExhibition(ThematicExhibitionDTO thematicExhibitionDTO);

    @Transactional
    ThematicExhibitionDTO updateThematicExhibition(ThematicExhibitionDTO thematicExhibitionDTO, Long id)  ;

    void deleteThematicExhibition(Long id) ;

    Page<ThematicExhibition> findAll(Pageable pageable, ThematicExhibitionDTO thematicExhibitionDTO);
}
