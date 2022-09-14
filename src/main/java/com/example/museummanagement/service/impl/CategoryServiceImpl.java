package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CategoryDTO;
import com.example.museummanagement.entity.Category;
import com.example.museummanagement.entity.CategoryDetail;
import com.example.museummanagement.repository.CategoryDetailRepository;
import com.example.museummanagement.repository.CategoryRepository;
import com.example.museummanagement.service.CategoryService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import javax.transaction.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryDetailRepository categoryDetailRepository;

    private final String CATEGORY = "category";


    @Transactional
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Optional<Category> cat = categoryRepository.findByName(categoryDTO.getName());
        Category category = new Category();
        if (null == cat) {
            category.setName(categoryDTO.getName());
            categoryRepository.save(category);
        } else {
            System.out.println("da ton tai san pham");
        }

        return categoryDTO;
    }


    @Override
    @Transactional
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {

        Optional<Category> category = categoryRepository.findByIdAndStatus(categoryDTO.getId(), Constants.STATUS_ACTIVE);
        Category cat = category.get();
        if(category.isPresent()) {
            Optional<Category> categoryName = categoryRepository.findByName(categoryDTO.getName());

            if (null == categoryName) {
                cat.setName(categoryDTO.getName());
                categoryRepository.save(cat);
            } else {
                System.out.println("San pham da ton tai");
            }
        } else {
            System.out.println("Lỗi không update được CSDL");
        }
        return categoryDTO;
    }

    @Override
    public Page<Category> getAllCategory(Pageable pageable, String name) {
        Page<Category> listCate = categoryRepository.getAllCategory(name, pageable);
        return listCate;
    }

    @Transactional
    @Override
    public Map<String, Object> deleteCategory(List<Long> id) {
        List<Category> categories = categoryRepository.findAllByIdInAndStatus (id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(categories)) {
            System.out.println("category code exits");
        }
        for (Category category : categories) {
            category.setStatus(Constants.STATUS_INACTIVE);
            category.setModifiedDate(new Date());
            categoryRepository.save(category);
        }
        List<CategoryDetail> categoryDetails = categoryDetailRepository.findAllByIdInAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(categoryDetails)) {
            System.out.println("category code exits");
        }

        for (CategoryDetail categoryDetail : categoryDetails) {
            categoryDetail.setStatus(Constants.STATUS_INACTIVE);
            categoryDetail.setModifiedDate(new Date());
            categoryDetailRepository.save(categoryDetail);
        }

        Map<String, Object> result = new HashMap<>();
        result.put(CATEGORY, categories);
        return result;
    }



    }


