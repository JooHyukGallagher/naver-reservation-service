package me.weekbelt.naverreservation.domain.product;

import me.weekbelt.naverreservation.domain.ImageType;

import java.util.List;

public interface ProductImageRepositoryCustom {
    List<ProductImage> findProductImageByProductIdAndType(Long productId, ImageType type);
}
