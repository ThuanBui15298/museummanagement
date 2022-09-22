package com.example.museummanagement.dto;


import lombok.Data;

@Data
public class PublicationTopicDTO {

    private String name;

    private  String content;

    private Integer type;

    private String title;

    private String slug;

    private String author;

    private String search;
}
