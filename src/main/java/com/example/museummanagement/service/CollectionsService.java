package com.example.museummanagement.service;


import com.example.museummanagement.dto.CollectionsDTO;
import com.example.museummanagement.entity.Collections;

import javax.transaction.Transactional;
import java.util.List;

public interface CollectionsService {

    @Transactional
    CollectionsDTO createCollections(CollectionsDTO collectionsDTO);

    @Transactional
    CollectionsDTO updateCollections(CollectionsDTO collectionsDTO, Long id)  ;

    void deleteCollections(Long id) ;

    List<Collections> findAllCollections();
}
