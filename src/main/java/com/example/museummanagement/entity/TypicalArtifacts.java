package com.example.museummanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "synthetic")
public class TypicalArtifacts extends BaseTimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer type;

    private  String name;

    private  String title;

    private String content;

    private Long categoryId;
}
