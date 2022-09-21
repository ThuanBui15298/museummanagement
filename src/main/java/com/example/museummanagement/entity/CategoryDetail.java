package com.example.museummanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "category_detail")
public class CategoryDetail extends BaseTimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    String title;

    String content;

    String description;

    int categoryId;
}
