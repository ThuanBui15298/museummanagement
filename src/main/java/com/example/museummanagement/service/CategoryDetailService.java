package com.example.museummanagement.service;

import com.example.museummanagement.dto.CategoryDetailDTO;
import com.example.museummanagement.entity.CategoryDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryDetailService {

    CategoryDetailDTO createCategoryDetail(CategoryDetailDTO categoryDetailDTO);

    CategoryDetailDTO updateCategoryDetail(CategoryDetailDTO categoryDetailDTO, Long id);

    void deleteCategoryDetail(Long id);

    List<CategoryDetail> getAllCategoryDetail();
}
