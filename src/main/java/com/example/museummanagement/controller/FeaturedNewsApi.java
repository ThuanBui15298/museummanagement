package com.example.museummanagement.controller;

import com.example.museummanagement.dto.FeaturedNewsDTO;
import com.example.museummanagement.dto.NewsDTO;
import com.example.museummanagement.entity.FeaturedNews;
import com.example.museummanagement.entity.News;
import com.example.museummanagement.service.FeaturedNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class FeaturedNewsApi {


    private final FeaturedNewsService featuredNewsService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createFeaturedNews(
            @RequestBody FeaturedNewsDTO featuredNewsDTO) throws Exception {
        featuredNewsService.createFeaturedNews(featuredNewsDTO);
        return new ResponseEntity(featuredNewsDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateFeaturedNews(
            @RequestBody FeaturedNewsDTO featuredNewsDTO,
            @PathVariable("id") Long id) {
        featuredNewsService.updateFeaturedNews(featuredNewsDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteFeaturedNews(@PathVariable("id") Long id) {
        featuredNewsService.deleteFeaturedNews(id);
        return new ResponseEntity<>("Deleted oke!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<FeaturedNews>> getAllNews() {
        return new ResponseEntity<>(featuredNewsService.findAll(), HttpStatus.OK);
    }
}
