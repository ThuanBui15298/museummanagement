package com.example.museummanagement.service;


import com.example.museummanagement.dto.CollectionsDTO;
import com.example.museummanagement.entity.Collections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface CollectionsService {

    @Transactional
    CollectionsDTO createCollections(CollectionsDTO collectionsDTO);

    @Transactional
    CollectionsDTO updateCollections(CollectionsDTO collectionsDTO, Long id)  ;

    void deleteCollections(Long id) ;

    Page<Collections> findAllCollections(Pageable pageable, CollectionsDTO collectionsDTO);
}
