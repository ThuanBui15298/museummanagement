package com.example.museummanagement.dto;

import lombok.Data;

@Data
public class FeaturedNewsDTO {
    private Long id;

    private String name;

    private  String content;

    private Integer type;

    private String title;

    private String slug;
}
