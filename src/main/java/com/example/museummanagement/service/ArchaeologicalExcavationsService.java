package com.example.museummanagement.service;

import com.example.museummanagement.dto.ArchaeologicalExcavationsDTO;
import com.example.museummanagement.entity.ArchaeologicalExcavations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface ArchaeologicalExcavationsService {
    @Transactional
    ArchaeologicalExcavationsDTO createArchaeologicalExcavations(ArchaeologicalExcavationsDTO archaeologicalExcavationsDTO);

    @Transactional
    ArchaeologicalExcavationsDTO updateArchaeologicalExcavations(ArchaeologicalExcavationsDTO archaeologicalExcavationsDTO, Long id)  ;

    void deleteArchaeologicalExcavations(Long id) ;

    Page<ArchaeologicalExcavations> findAllArchaeologicalExcavations(Pageable pageable, ArchaeologicalExcavationsDTO archaeologicalExcavationsDTO);
}
