package me.weekbelt.naverreservation.domain.productImage;

import me.weekbelt.naverreservation.domain.ImageType;

import java.util.List;

public interface ProductImageRepositoryCustom {
    List<ProductImage> findProductImageByProductIdAndType(Long productId, ImageType type);
}
