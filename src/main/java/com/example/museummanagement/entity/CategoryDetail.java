package com.example.museummanagement.entity;


import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="category_detail")
public class CategoryDetail extends  BaseTimeModel<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tên không được trống")
    @Column(name = "name")
    private String name;

    @JoinColumn(name = "category_id")
    private Long categoryId;
}
