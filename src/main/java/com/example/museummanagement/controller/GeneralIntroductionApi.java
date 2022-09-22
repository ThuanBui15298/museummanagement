package com.example.museummanagement.controller;

import com.example.museummanagement.dto.GeneralIntroductionDTO;
import com.example.museummanagement.entity.GeneralIntroduction;
import com.example.museummanagement.service.GeneralIntroductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/general-introduction")
@RequiredArgsConstructor
public class GeneralIntroductionApi {

        private final GeneralIntroductionService generalIntroductionService;

        @PostMapping(value = "/create")
        public ResponseEntity<?> createGeneralIntroduction(
                @RequestBody GeneralIntroductionDTO generalIntroductionDTO) {
            generalIntroductionService.createGeneralIntroduction(generalIntroductionDTO);
            return new ResponseEntity<>(generalIntroductionDTO, HttpStatus.OK);
        }

        @PutMapping(value = "/update/{id}")
        public ResponseEntity<?> updateGeneralIntroduction(
                @RequestBody GeneralIntroductionDTO generalIntroductionDTO,
                @PathVariable("id") Long id) {
            generalIntroductionService.updateGeneralIntroduction(generalIntroductionDTO, id);
            return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
        }

        @PutMapping(value = "/delete/{id}")
        public ResponseEntity<?> deleteGeneralIntroduction(@PathVariable("id") Long id) {
            generalIntroductionService.deleteGeneralIntroduction(id);
            return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
        }

        @GetMapping("/get-all")
        public ResponseEntity<Page<GeneralIntroduction>> findAllGeneralIntroduction(Pageable pageable,
                                                                                    @RequestBody GeneralIntroductionDTO generalIntroductionDTO) {
            return new ResponseEntity<>(generalIntroductionService.findAllGeneralIntroduction(pageable, generalIntroductionDTO), HttpStatus.OK);
        }
}
