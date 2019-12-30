package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.category.Category;
import me.weekbelt.naverreservation.domain.category.CategoryRepository;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepository;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepositoryImpl;
import me.weekbelt.naverreservation.web.dto.category.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final DisplayInfoRepository displayInfoRepository;

    public List<CategoryDto> findCategoryDto(){
        List<Category> categories = categoryRepository.findAll();
        return createCategoryDtos(categories);
    }

    private List<CategoryDto> createCategoryDtos(List<Category> categories) {
        return categories.stream()
                .map(category -> {
                    Integer count = displayInfoRepository.countDisplayInfoNumberByCategoryId(category.getId());
                    return new CategoryDto(category, count);
                })
                .collect(Collectors.toList());
    }
}
