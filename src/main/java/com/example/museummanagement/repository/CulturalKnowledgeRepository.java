package com.example.museummanagement.repository;

import com.example.museummanagement.entity.CulturalKnowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CulturalKnowledgeRepository extends JpaRepository<CulturalKnowledge, Long> {

    Optional<CulturalKnowledge> findByName(String name);

    List<CulturalKnowledge> findAllByIdAndStatus(Long id, Integer status);

    @Query(value = "select * from news n where n.status = 1 and n.type = 8 and lower(concat(coalesce(n.name,''), coalesce(n.title ,''))) like lower(:search)", nativeQuery = true)
    Page<CulturalKnowledge> findAllBySearch(Pageable pageable, @Param("search") String search);
}
