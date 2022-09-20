package com.example.museummanagement.controller;

import com.example.museummanagement.entity.OpenTime;
import com.example.museummanagement.service.OpenTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open-time")
public class OpenTimeApi {

    @Autowired
    private OpenTimeService openTimeService;

    @PostMapping("/create")
    public ResponseEntity<?> createOpenTime(@RequestBody OpenTime openTime) {
        openTimeService.createOpentime(openTime);
        return new ResponseEntity<>(openTime, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateOpenTime(@RequestBody OpenTime openTime, @PathVariable("id") Long id) {
        openTimeService.updateOpenTime(openTime, id);
        return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
    }

    @PutMapping("delete/{id}")
    public ResponseEntity<?> deleteOpenTime(@PathVariable("id") Long id) {
        openTimeService.deleteOpenTime(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<OpenTime>> getAllOpenTime() {
        return new ResponseEntity<>(openTimeService.getAllOpenTime(), HttpStatus.OK);
    }

}
