package com.example.museummanagement.controller;

import com.example.museummanagement.dto.CategoryDTO;
import com.example.museummanagement.entity.Category;
import com.example.museummanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryApi {

    private final CategoryService categoryService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCategory(
                                             @RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity(categoryDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCategory(
                                        @RequestBody CategoryDTO categoryDTO,
                                        @PathVariable("id") Long id) {
        categoryService.updateCategory(categoryDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestBody List<Long> id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/category")
    public ResponseEntity<?> getAllCategory(@RequestParam String name,
            Pageable pageable){
        categoryService.getAllCategory( pageable, name);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}