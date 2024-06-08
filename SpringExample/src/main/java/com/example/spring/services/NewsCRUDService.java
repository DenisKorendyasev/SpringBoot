package com.example.spring.services;

import com.example.spring.dto.CategoryDto;
import com.example.spring.dto.NewsDto;
import com.example.spring.entity.Category;
import com.example.spring.entity.News;
import com.example.spring.repository.CategoryRepository;
import com.example.spring.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCRUDService implements CRUDService<NewsDto>{

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public NewsDto getById(Integer id) {
        log.info("Calling a method getById in a class NewsCRUDService");
        return mapToDto(newsRepository.findById(id).orElseThrow());
    }

    @Override
    public Collection<NewsDto> getAll() {
        log.info("Calling a method getAll in a class NewsCRUDService");
        return newsRepository.findAll()
                .stream()
                .map(NewsCRUDService::mapToDto)
                .toList();
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        log.info("Calling a method create in a class NewsCRUDService");
        News news = mapToEntity(newsDto);
        Integer categoryId = newsDto.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        news.setCategory(category);
        newsRepository.save(news);
        return newsDto;
    }

    @Override
    public NewsDto update(Integer id, NewsDto newsDto) {
        log.info("Calling a method update in a class NewsCRUDService");
        if (newsRepository.existsById(id)) {
            News news = mapToEntity(newsDto);
            Integer categoryId = newsDto.getCategoryId();
            Category category = categoryRepository.findById(categoryId).orElseThrow();
            news.setCategory(category);
            newsRepository.save(news);
            return newsDto;
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        log.info("Calling a method delete in a class NewsCRUDService");
        newsRepository.deleteById(id);
    }

    public static News mapToEntity(NewsDto newsDto){
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        news.setInstant(newsDto.getInstant());
        news.setCategory(news.getCategory());
        return news;
    }

    public static NewsDto mapToDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setText(news.getText());
        newsDto.setInstant(news.getInstant());
        newsDto.setCategoryId(news.getCategory().getId());
        return newsDto;
    }
}
