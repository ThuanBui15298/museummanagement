package com.example.museummanagement.dto;

import lombok.Data;

@Data
public class InstructionDTO {

    private Integer type;

    private  String name;

    private  String title;

    private String content;

    private Long categoryId;
}
