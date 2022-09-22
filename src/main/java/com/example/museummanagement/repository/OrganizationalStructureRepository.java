package com.example.museummanagement.repository;

import com.example.museummanagement.entity.OrganizationalStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrganizationalStructureRepository extends JpaRepository<OrganizationalStructure, Long> {

    Optional<OrganizationalStructure> findByName(String name);

    List<OrganizationalStructure> findAllByIdAndStatus(Long id, Integer status);

}
