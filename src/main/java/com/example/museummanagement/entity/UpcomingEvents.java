package com.example.museummanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "news")
public class UpcomingEvents extends BaseTimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer type;

    private String name;

    private String title;

    private String slug;

    private String description;

    private String content;

    private Long categoryDetailId;

    private  String author;
}
