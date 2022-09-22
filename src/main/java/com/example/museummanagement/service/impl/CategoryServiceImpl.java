package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CategoryDTO;
import com.example.museummanagement.entity.Category;
import com.example.museummanagement.entity.CategoryDetail;
import com.example.museummanagement.repository.CategoryDetailRepository;
import com.example.museummanagement.repository.CategoryRepository;
import com.example.museummanagement.service.CategoryService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public static final String  CATEGORY = "Category";

    private final CategoryDetailRepository categoryDetailRepository;

    @Transactional
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception {
        validRequest(categoryDTO);
        Optional<Category> cat = categoryRepository.findByName(categoryDTO.getName());
        Category category = new Category();
        if (cat.isEmpty()) {
            category.setName(categoryDTO.getName());
            category.setStatus(Constants.STATUS_ACTIVE);
            categoryRepository.save(category);
        } else {
            throw new MessageDescriptorFormatException("Name existed!");
        }
        return categoryDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
        validRequest(categoryDTO);
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category cat = category.get();

            Optional<Category> categoryName = categoryRepository.findByName(categoryDTO.getName());
            if (categoryName.isEmpty() || cat.getId().equals(categoryName.get().getId())) {
                cat.setName(categoryDTO.getName());
                cat.setStatus(Constants.STATUS_ACTIVE);
                categoryRepository.save(cat);
            } else {
                throw new MessageDescriptorFormatException("Name existed");
            }
        } else {
            throw new MessageDescriptorFormatException("Can not found by id: " + id);
        }
        return categoryDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public Map<String, Object> deleteCategory(Long id) {
        List<Category> categories = categoryRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(categories)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (Category category : categories) {
            category.setStatus(Constants.STATUS_INACTIVE);
            category.setModifiedDate(new Date());
            categoryRepository.save(category);
        }

        List<CategoryDetail> categoryDetailList = categoryDetailRepository.findByCategoryIdAndStatus();
        if (CollectionUtils.isEmpty(categoryDetailList)) {
            throw new MessageDescriptorFormatException("Can not found!");
        }
        for (CategoryDetail categoryDetail: categoryDetailList) {
            categoryDetail.setStatus(Constants.STATUS_INACTIVE);
            categoryDetailRepository.save(categoryDetail);
        }

        Map<String, Object> result = new HashMap<>();
        result.put(CATEGORY, categories);
        return result;
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @SneakyThrows
    private void validRequest(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new MessageDescriptorFormatException("Request invalid");
        }

    }

}
