package com.example.museummanagement.controller;

import com.example.museummanagement.dto.PublicationTopicDTO;
import com.example.museummanagement.entity.PublicationTopic;
import com.example.museummanagement.service.PublicationTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publication-topic")
@RequiredArgsConstructor
public class PublicationTopicApi {

    private final PublicationTopicService publicationTopicService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createPublicationTopic(
            @RequestBody PublicationTopicDTO publicationTopicDTO) {
        publicationTopicService.createPublicationTopic(publicationTopicDTO);
        return new ResponseEntity<>(publicationTopicDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updatePublicationTopic(
            @RequestBody PublicationTopicDTO publicationTopicDTO,
            @PathVariable("id") Long id) {
        publicationTopicService.updatePublicationTopic(publicationTopicDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePublicationTopic(@PathVariable("id") Long id) {
        publicationTopicService.deletePublicationTopic(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<PublicationTopic>> getAllPublicationTopic(Pageable pageable,
                                                                         @RequestBody PublicationTopicDTO publicationTopicDTO) {
        return new ResponseEntity<>(publicationTopicService.findAllPublicationTopic(pageable, publicationTopicDTO), HttpStatus.OK);
    }
}
