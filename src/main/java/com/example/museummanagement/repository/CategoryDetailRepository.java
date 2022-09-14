package com.example.museummanagement.repository;

import com.example.museummanagement.entity.Category;
import com.example.museummanagement.entity.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long> {

    List<CategoryDetail> findAllByIdInAndStatus(List<Long> id, Integer status);

}
