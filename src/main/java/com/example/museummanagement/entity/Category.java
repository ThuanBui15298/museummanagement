package com.example.museummanagement.entity;


import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="category")
public class Category extends  BaseTimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Tên danh mục không được trống")
    @Column(name = "name")
    private String name;

}
