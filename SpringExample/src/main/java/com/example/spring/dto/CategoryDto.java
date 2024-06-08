package com.example.spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Integer id;
    private String title;
    private List<NewsDto> news;
}
