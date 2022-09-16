package com.example.museummanagement.controller;

import com.example.museummanagement.dto.NewsDTO;
import com.example.museummanagement.entity.News;
import com.example.museummanagement.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsApi {

    @Autowired
    private NewsService newsService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNews(
            @RequestBody NewsDTO newsDTO) throws Exception {
        newsService.createNews(newsDTO);
        return new ResponseEntity(newsDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateNews(
            @RequestBody NewsDTO newsDTO,
            @PathVariable("id") Long id) {
        newsService.updateNews(newsDTO, id);
        return new ResponseEntity<>(newsDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>("oke", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<News>> getAllNews() {
        return new ResponseEntity<>(newsService.findAll(), HttpStatus.OK);
    }
}
