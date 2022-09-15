package com.example.museummanagement.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="videos")
public class Videos extends  BaseTimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String slug;

}
