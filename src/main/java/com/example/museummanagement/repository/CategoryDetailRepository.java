package com.example.museummanagement.repository;

import com.example.museummanagement.entity.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long> {
    Optional<CategoryDetail> findByName(String name);

    List<CategoryDetail> findAllByIdAndStatus(Long id, Integer status);
}
