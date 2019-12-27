package me.weekbelt.naverreservation.web.dto.product;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.product.Promotion;

@Getter @Setter
public class PromotionDto {

    // promotion 테이블
    private Long id;
    private Long productId;

    // file_info 테이블
    private String productImageUrl;

    public PromotionDto(Promotion promotion, String productImageUrl) {
        this.id = promotion.getId();
        this.productId = promotion.getProduct().getId();
        this.productImageUrl = productImageUrl;
    }
}
