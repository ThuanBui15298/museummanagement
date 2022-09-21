package com.example.museummanagement.controller;

import com.example.museummanagement.dto.CategoryDetailDTO;
import com.example.museummanagement.entity.CategoryDetail;
import com.example.museummanagement.service.CategoryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category-detail")
@RequiredArgsConstructor
public class CategoryDetailApi {

    private final CategoryDetailService categoryDetailService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategoryDetail(@RequestBody CategoryDetailDTO categoryDetailDTO) {
        categoryDetailService.createCategoryDetail(categoryDetailDTO);
        return new ResponseEntity<>(categoryDetailDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategoryDetail(@RequestBody CategoryDetailDTO categoryDetailDTO,
                                                  @PathVariable("id") Long id) {
        categoryDetailService.updateCategoryDetail(categoryDetailDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delelteCategoryDetail(@PathVariable("id") Long id) {
        categoryDetailService.deleteCategoryDetail(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDetail>> getAllCategoryDetail() {
        return new ResponseEntity<>(categoryDetailService.getAllCategoryDetail(), HttpStatus.OK);
    }
}
