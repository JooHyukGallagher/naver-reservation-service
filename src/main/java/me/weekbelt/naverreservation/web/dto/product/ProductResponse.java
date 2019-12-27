package me.weekbelt.naverreservation.web.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductResponse {
    private Integer totalCount;
    private List<ProductDto> items;

    @Builder
    public ProductResponse(Integer totalCount, List<ProductDto> items) {
        this.totalCount = totalCount;
        this.items = items;
    }
}
