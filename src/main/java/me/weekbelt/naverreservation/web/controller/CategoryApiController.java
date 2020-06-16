package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.category.CategoryRepository;
import me.weekbelt.naverreservation.web.dto.category.CategoryDto;
import me.weekbelt.naverreservation.web.dto.category.CategoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public CategoryResponse getCategoryResponse() {
        return CategoryResponse.builder()
                .items(categoryRepository.findCategoryDtos())
                .build();
    }
}
