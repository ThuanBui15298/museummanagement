package com.example.museummanagement.dto;

import lombok.Data;

@Data
public class NewsDTO {

    private Long id;

    private String name;

    private  String content;

    private String description;

    private Integer type;

    private String title;

    private String slug;

}
