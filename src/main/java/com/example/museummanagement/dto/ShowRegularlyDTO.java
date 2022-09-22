package com.example.museummanagement.dto;

import lombok.Data;

@Data
public class ShowRegularlyDTO {

    private Long id;

    private Integer type;

    private String name;

    private String title;

    private String slug;

    private String content;

    private Long categoryId;

    private String author;

    private String search;
}
