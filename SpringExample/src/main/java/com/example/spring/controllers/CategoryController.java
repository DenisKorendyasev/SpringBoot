package com.example.spring.controllers;

import com.example.spring.dto.CategoryDto;
import com.example.spring.dto.NewsDto;
import com.example.spring.services.CategoryCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api/categories")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryCRUDService category;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(category.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<CategoryDto>> getAll(){
        return new ResponseEntity<>(category.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(category.create(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Integer id,
                                              @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        return new ResponseEntity<>(category.update(id, categoryDto), HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        category.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
