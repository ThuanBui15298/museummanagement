package com.example.museummanagement.controller;

import com.example.museummanagement.dto.MediaDTO;
import com.example.museummanagement.entity.Media;
import com.example.museummanagement.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/media")
public class MediaApi {

    private final MediaService mediaService;

    @PostMapping("/create")
    public ResponseEntity<?> createMedia(@RequestBody MediaDTO mediaDTO) {
        mediaService.createMedia(mediaDTO);
        return new ResponseEntity<>(mediaDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMedia(@RequestBody MediaDTO mediaDTO, @PathVariable("id") Long id) {
        mediaService.updateMedia(mediaDTO, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedia(@PathVariable("id") Long id) {
        mediaService.deleteMedia(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @GetMapping("get-all-videos")
    public ResponseEntity<List<Media>> getAllMedia() {
        return new ResponseEntity<>(mediaService.getAllMedia(), HttpStatus.OK);
    }
}
