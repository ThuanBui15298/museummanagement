package com.example.museummanagement.service.impl;

import com.example.museummanagement.dto.CategoryDetailDTO;
import com.example.museummanagement.entity.CategoryDetail;
import com.example.museummanagement.repository.CategoryDetailRepository;
import com.example.museummanagement.service.CategoryDetailService;
import com.example.museummanagement.ulti.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryDetailServiceImpl implements CategoryDetailService {

    private final CategoryDetailRepository categoryDetailRepository;

    @SneakyThrows
    @Override
    public CategoryDetailDTO createCategoryDetail(CategoryDetailDTO categoryDetailDTO) {
        Optional<CategoryDetail> optionalCategoryDetail = categoryDetailRepository.findByName(categoryDetailDTO.getName());
        CategoryDetail categoryDetail = new CategoryDetail();
        if (optionalCategoryDetail.isEmpty()) {
            categoryDetail.setName(categoryDetailDTO.getName());
            categoryDetail.setTitle(categoryDetailDTO.getTitle());
            categoryDetail.setContent(categoryDetailDTO.getContent());
            categoryDetail.setDescription(categoryDetailDTO.getDescription());
            categoryDetail.setStatus(Constants.STATUS_ACTIVE);
            categoryDetailRepository.save(categoryDetail);
        } else {
            throw new Exception("Name existed!");
        }
        return categoryDetailDTO;
    }

    @SneakyThrows
    @Override
    public CategoryDetailDTO updateCategoryDetail(CategoryDetailDTO categoryDetailDTO, Long id) {
        Optional<CategoryDetail> optionalCategoryDetail = categoryDetailRepository.findById(id);
        if (optionalCategoryDetail.isPresent()) {
            CategoryDetail categoryDetail = optionalCategoryDetail.get();
            Optional<CategoryDetail> optional = categoryDetailRepository.findByName(categoryDetailDTO.getName());
            if (optional.isEmpty() || categoryDetail.getId().equals(optional.get().getId())) {
                categoryDetail.setName(categoryDetailDTO.getName());
                categoryDetail.setTitle(categoryDetailDTO.getTitle());
                categoryDetail.setContent(categoryDetailDTO.getContent());
                categoryDetail.setDescription(categoryDetailDTO.getDescription());
                categoryDetail.setStatus(Constants.STATUS_ACTIVE);
                categoryDetailRepository.save(categoryDetail);
            } else {
                throw new Exception("Name existed!");
            }
        } else {
            throw new Exception("Can not found by id: !" + id);
        }
        return categoryDetailDTO;
    }

    @SneakyThrows
    @Override
    public void deleteCategoryDetail(Long id) {
        List<CategoryDetail> categoryDetailList = categoryDetailRepository.findAllByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(categoryDetailList)) {
            throw new Exception("Can not found!");
        }
        for (CategoryDetail categoryDetail : categoryDetailList) {
            categoryDetail.setStatus(Constants.STATUS_INACTIVE);
            categoryDetailRepository.save(categoryDetail);
        }
    }

    @Override
    public List<CategoryDetail> getAllCategoryDetail() {
        return categoryDetailRepository.findAll();
    }
}
