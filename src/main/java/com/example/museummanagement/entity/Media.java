package com.example.museummanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="media")
public class Media extends BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String objectType;

    private String link;

    private Integer type;

    private  Long albumId;

    private  Long videoId;

}
