package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.category.Category;
import me.weekbelt.naverreservation.domain.category.CategoryRepository;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepositoryImpl;
import me.weekbelt.naverreservation.web.dto.category.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final DisplayInfoRepositoryImpl displayInfoRepository;

    public List<CategoryDto> findCategoryDto(){
        List<Category> categories = categoryRepository.findAll();

        return createCategoryDtos(categories);
    }

    private List<CategoryDto> createCategoryDtos(List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            Integer count = displayInfoRepository.countDisplayInfoNumberByCategoryId(category.getId());
            CategoryDto categoryDto = new CategoryDto(category, count);
            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }


}
