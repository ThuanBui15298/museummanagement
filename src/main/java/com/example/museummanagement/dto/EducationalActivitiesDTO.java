package com.example.museummanagement.dto;

import lombok.Data;

@Data
public class EducationalActivitiesDTO {

    private Integer type;

    private String name;

    private String title;

    private String slug;

    private String description;

    private String content;

    private Long categoryId;

    private  String author;

    private String search;
}
