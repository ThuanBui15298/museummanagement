package com.example.museummanagement.service;

import com.example.museummanagement.dto.ThematicExhibitionDTO;
import com.example.museummanagement.entity.ThematicExhibition;

import javax.transaction.Transactional;
import java.util.List;

public interface ThematicExhibitionService {

    @Transactional
    ThematicExhibitionDTO createThematicExhibition(ThematicExhibitionDTO thematicExhibitionDTO);

    @Transactional
    ThematicExhibitionDTO updateThematicExhibition(ThematicExhibitionDTO thematicExhibitionDTO, Long id)  ;

    void deleteThematicExhibition(Long id) ;

    List<ThematicExhibition> findAll();
}
