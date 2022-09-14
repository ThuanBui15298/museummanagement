package com.example.museummanagement.service;

import com.example.museummanagement.dto.CategoryDTO;
import com.example.museummanagement.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    @Transactional
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    @Transactional
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

    Page<Category> getAllCategory(Pageable pageable, String name);

    Map<String,Object> deleteCategory(List<Long> id);

}
