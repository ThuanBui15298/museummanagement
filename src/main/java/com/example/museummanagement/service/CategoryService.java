package com.example.museummanagement.service;

import com.example.museummanagement.dto.CategoryDTO;
import com.example.museummanagement.entity.Category;

import javax.transaction.Transactional;
import java.util.Map;


public interface CategoryService {

    @Transactional
    CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception;

    @Transactional
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id)  ;

    Map<String, Object> deleteCategory(Long id) ;

    Iterable<Category> findAll();

}
