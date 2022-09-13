package com.example.museummanagement.entity;


import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="synthetic")
public class Synthetic extends  BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tên không được trống")
    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @JoinColumn(name = "category_id")
    private Long categoryId;

    @JoinColumn(name = "category_detail_id")
    private Long categoryDetailId;


//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id")
//    private List<Image> image;


}
