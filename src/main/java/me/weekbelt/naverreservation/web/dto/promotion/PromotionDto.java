package me.weekbelt.naverreservation.web.dto.promotion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.weekbelt.naverreservation.domain.promotion.Promotion;

@ToString
@NoArgsConstructor
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
