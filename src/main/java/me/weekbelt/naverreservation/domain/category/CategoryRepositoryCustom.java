package me.weekbelt.naverreservation.domain.category;

import me.weekbelt.naverreservation.web.dto.category.CategoryDto;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<CategoryDto> findCategoryDto();
}
