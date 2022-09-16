package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CategoryDTO;
import com.example.museummanagement.entity.Category;
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
            throw new MessageDescriptorFormatException("Name da ton tai");
        }
        return categoryDTO;
    }

    @SneakyThrows
    @Override
    @Transactional
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
        validRequest(categoryDTO);
        Optional<Category> category = categoryRepository.findById(id);
        Category cat = category.get();
        if (category.isPresent()) {
            Optional<Category> categoryName = categoryRepository.findByName(categoryDTO.getName());
            if (categoryName.isEmpty()) {
                cat.setName(categoryDTO.getName());
                cat.setStatus(Constants.STATUS_ACTIVE);
                categoryRepository.save(cat);
            } else {
                throw new MessageDescriptorFormatException("Name Danh muc da ton tai");
            }
        } else {
            throw new MessageDescriptorFormatException("Id khong ton tai");
        }
        return categoryDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteCategory(Long id) {
        List<Category> categories = categoryRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(categories)) {
            throw new MessageDescriptorFormatException("Khong ton tai");
        }
        for (Category category : categories) {
            category.setStatus(Constants.STATUS_INACTIVE);
            category.setModifiedDate(new Date());
            categoryRepository.save(category);
        }
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


