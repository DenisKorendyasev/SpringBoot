package com.example.spring.controllers;

import com.example.spring.dto.NewsDto;
import com.example.spring.services.NewsCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsCRUDService newsService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<NewsDto> getIdNews(@PathVariable Integer id) {
        if (newsService.getById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newsService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<NewsDto>> getAllNews() {
        return new ResponseEntity<>(newsService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsDto news) {
        return new ResponseEntity<>(newsService.create(news), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<NewsDto> updateNews(@PathVariable Integer id, @RequestBody NewsDto news) {
        return new ResponseEntity<>(newsService.update(id, news), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Integer id) {
        if (newsService.getById(id) == null) {
            return new ResponseEntity<>("Новость с ID " + id + " не найдена", HttpStatus.NOT_FOUND);
        }
        newsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
