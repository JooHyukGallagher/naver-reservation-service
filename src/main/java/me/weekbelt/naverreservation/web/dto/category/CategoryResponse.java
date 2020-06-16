package me.weekbelt.naverreservation.web.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class CategoryResponse {
    List<CategoryDto> items;

    @Builder
    public CategoryResponse(List<CategoryDto> items) {
        this.items = items;
    }
}
