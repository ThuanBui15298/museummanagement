package com.example.museummanagement.dto;

import lombok.Data;

@Data
public class GeneralIntroductionDTO {

    private Integer type;

    private  String name;

    private  String title;

    private String content;

    private Long categoryId;

    private String search;
}
