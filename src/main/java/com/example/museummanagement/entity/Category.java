package com.example.museummanagement.entity;

import lombok.*;
import org.apache.bval.constraints.NotEmpty;

import javax.persistence.*;


@Entity
@Data
@Table(name="category")
public class Category extends  BaseTimeModel<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Tên danh mục không được trống")
    @Column(name = "name")
    private String name;


}
