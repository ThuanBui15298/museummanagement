package com.example.museummanagement.controller;

import com.example.museummanagement.entity.Album;
import com.example.museummanagement.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumApi {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/create")
    public ResponseEntity<?> createAlbum(@RequestBody Album album) {
        return new ResponseEntity<>(albumService.createAlbum(album), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {
        albumService.updateAlbum(album,id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable("id") Long id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Album>> getALlAlbum() {
        return new ResponseEntity<>(albumService.getALlAlbum(), HttpStatus.OK);
    }
}
