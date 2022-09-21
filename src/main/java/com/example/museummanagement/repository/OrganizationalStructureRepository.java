package com.example.museummanagement.repository;

import com.example.museummanagement.entity.OrganizationalStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationalStructureRepository extends JpaRepository<OrganizationalStructure, Long> {

    Optional<OrganizationalStructure> findByName(String name);

    List<OrganizationalStructure> findAllByIdAndStatus(Long id, Integer status);
}
