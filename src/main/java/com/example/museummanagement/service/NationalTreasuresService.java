package com.example.museummanagement.service;

import com.example.museummanagement.dto.NationalTreasuresDTO;
import com.example.museummanagement.entity.NationalTreasures;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface NationalTreasuresService {
    @Transactional
    NationalTreasuresDTO createNationalTreasure(NationalTreasuresDTO nationalTreasuresDTO);

    @Transactional
    NationalTreasuresDTO updateNationalTreasure(NationalTreasuresDTO nationalTreasuresDTO, Long id);

    void deleteNationalTreasure(Long id);

    List<NationalTreasures> getAllNationalTreasure();
}
