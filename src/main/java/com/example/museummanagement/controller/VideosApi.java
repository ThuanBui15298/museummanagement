package com.example.museummanagement.controller;

import com.example.museummanagement.entity.Videos;
import com.example.museummanagement.service.VideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/videos")
public class VideosApi {

    private final VideosService videosService;

    @PostMapping("/create")
    public ResponseEntity<?> createVideos(@RequestBody Videos video) {
        videosService.createVideo(video);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVideos(@RequestBody Videos video, @PathVariable("id") Long id) {
        videosService.upadateVideo(video, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteVideos(@PathVariable("id") Long id) {
        videosService.deleteVideo(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @GetMapping("get-all-videos")
    public ResponseEntity<List<Videos>> getAllVideos() {
        return new ResponseEntity<>(videosService.getAllVideos(), HttpStatus.OK);
    }
}
