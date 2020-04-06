package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.category.CategoryRepository;
import me.weekbelt.naverreservation.web.dto.category.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> findCategoryDto() {
        return categoryRepository.findCategoryDto();
    }
}
