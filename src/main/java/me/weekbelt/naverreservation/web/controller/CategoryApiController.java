package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.service.CategoryService;
import me.weekbelt.naverreservation.web.dto.category.CategoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public CategoryResponse getCategoryResponse() {
        return new CategoryResponse(categoryService.findCategoryDtos());
    }
}
