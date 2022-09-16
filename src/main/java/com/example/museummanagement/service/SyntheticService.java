package com.example.museummanagement.service;

import com.example.museummanagement.entity.Synthetic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface SyntheticService {

    @Transactional
    Synthetic createSynthetic(Synthetic synthetic);

    @Transactional
    Synthetic updateSynthetic(Synthetic synthetic, Long id);

    @Transactional
    void deteleSynthetic(Long id);

    List<Synthetic> getAllSynthetic();
}
