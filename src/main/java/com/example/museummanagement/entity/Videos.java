package com.example.museummanagement.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="videos")
public class Videos extends  BaseTimeModel<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String slug;

}
