package me.weekbelt.naverreservation.domain.product;

import me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo;
import me.weekbelt.naverreservation.web.dto.product.ProductDto;

public class ProductFactoryObject {
    public static ProductDto toProductDto(DisplayInfo displayInfo, String saveFileName) {
        return ProductDto.builder()
                .displayInfoId(displayInfo.getId())
                .productId(displayInfo.getProduct().getId())
                .productDescription(displayInfo.getProduct().getDescription())
                .placeName(displayInfo.getPlaceName())
                .productContent(displayInfo.getProduct().getContent())
                .productImageUrl(saveFileName)
                .build();
    }
}
