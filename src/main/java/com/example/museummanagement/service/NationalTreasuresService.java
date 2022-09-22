package com.example.museummanagement.service;

import com.example.museummanagement.dto.NationalTreasuresDTO;
import com.example.museummanagement.entity.NationalTreasures;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public interface NationalTreasuresService {
    @Transactional
    NationalTreasuresDTO createNationalTreasure(NationalTreasuresDTO nationalTreasuresDTO);

    @Transactional
    NationalTreasuresDTO updateNationalTreasure(NationalTreasuresDTO nationalTreasuresDTO, Long id);

    void deleteNationalTreasure(Long id);

    Page<NationalTreasures> findAllNationalTreasures(Pageable pageable, NationalTreasuresDTO nationalTreasuresDTO);
}
