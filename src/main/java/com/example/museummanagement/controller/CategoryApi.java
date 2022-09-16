package com.example.museummanagement.controller;

import com.example.museummanagement.dto.CategoryDTO;
import com.example.museummanagement.entity.Category;
import com.example.museummanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCategory(
            @RequestBody CategoryDTO categoryDTO) throws Exception {
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity(categoryDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCategory(
            @RequestBody CategoryDTO categoryDTO,
            @PathVariable("id") Long id) {
        categoryService.updateCategory(categoryDTO, id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {

        categoryService.deleteCategory(id);
        return new ResponseEntity<>("oke", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<Category>> getAllCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
}