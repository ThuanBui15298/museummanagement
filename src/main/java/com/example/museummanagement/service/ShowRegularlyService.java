package com.example.museummanagement.service;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.ShowRegularlyDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.ShowRegularly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ShowRegularlyService {
    @Transactional
    ShowRegularlyDTO createShowRegularly(ShowRegularlyDTO showRegularlyDTO);

    @Transactional
    ShowRegularlyDTO updateShowRegularly(ShowRegularlyDTO showRegularlyDTO, Long id);

    void deleteShowRegularly(Long id);

    Page<ShowRegularly> findAllShowRegularly(Pageable pageable, ShowRegularlyDTO showRegularlyDTO);


}
