package com.example.museummanagement.repository;

import com.example.museummanagement.entity.GeneralIntroduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeneralIntroductionRepository extends JpaRepository<GeneralIntroduction, Long> {

    Optional<GeneralIntroduction> findByName(String name);

    List<GeneralIntroduction> findAllByIdAndStatus(Long id, Integer status);

}
