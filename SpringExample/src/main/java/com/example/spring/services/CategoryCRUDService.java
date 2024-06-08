package com.example.spring.services;

import com.example.spring.dto.CategoryDto;
import com.example.spring.entity.Category;
import com.example.spring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryCRUDService implements CRUDService<CategoryDto> {

    private final CategoryRepository categoryRepository;
    @Override
    public CategoryDto getById(Integer id) {
        log.info("Calling a method getById in a class CategoryCRUDService");
        return mapToDto(categoryRepository.findById(id).orElseThrow());
    }

    @Override
    public Collection<CategoryDto> getAll() {
        log.debug("Calling a method getAll in a class CategoryCRUDService");
        return categoryRepository.findAll()
                .stream()
                .map(CategoryCRUDService::mapToDto)
                .toList();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        log.info("Calling a method create in a class CategoryCRUDService");
        categoryRepository.save(mapToEntity(categoryDto));
        return categoryDto;
    }

    @Override
    public CategoryDto update(Integer id, CategoryDto categoryDto)
    {
        log.info("Calling a method update in a class CategoryCRUDService");
        if (categoryRepository.existsById(id)) {
            categoryRepository.save(mapToEntity(categoryDto));
            return categoryDto;
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        log.info("Calling a method delete in a class CategoryCRUDService");
        categoryRepository.deleteById(id);
    }

    public static Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setNews(
                categoryDto.getNews()
                        .stream()
                        .map(NewsCRUDService::mapToEntity)
                        .toList());

        return category;
    }

    public static CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setNews(
                category.getNews()
                        .stream()
                        .map(NewsCRUDService::mapToDto)
                        .toList());

        return categoryDto;
    }
}
