package com.example.museummanagement.entity;

import lombok.*;
import org.apache.bval.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category extends  BaseTimeModel<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Tên danh mục không được trống")
    @Column(name = "name")
    private String name;

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
