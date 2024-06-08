package com.example.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Data
public class NewsDto {

    private int id;
    private String title;
    private String text;
    private Instant instant;
    private Integer categoryId;
}
