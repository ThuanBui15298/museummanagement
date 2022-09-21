package com.example.museummanagement.service;

import com.example.museummanagement.dto.OrganizationalStructureDTO;
import com.example.museummanagement.entity.OrganizationalStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


public interface OrganizationalStructureService {

    @Transactional
    OrganizationalStructureDTO createOrganizationalStructure(OrganizationalStructureDTO organizationalStructureDTO);

    @Transactional
    OrganizationalStructureDTO updateOrganizationalStructure(OrganizationalStructureDTO organizationalStructureDTO, Long id);

    void deleteOrganizationalStructure(Long id);

    List<OrganizationalStructure> getAllOrganizationalStructure();

}
