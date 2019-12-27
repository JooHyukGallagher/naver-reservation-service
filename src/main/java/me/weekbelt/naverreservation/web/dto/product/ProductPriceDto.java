package me.weekbelt.naverreservation.web.dto.product;

import lombok.Data;
import me.weekbelt.naverreservation.domain.product.ProductPrice;

import java.time.LocalDateTime;

@Data
public class ProductPriceDto {

    // product_price 테이블
    private Long productPriceId;
    private Long productId;
    private String priceTypeName;
    private Integer price;
    private Double discountRate;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ProductPriceDto(ProductPrice productPrice) {
        this.productPriceId = productPrice.getId();
        this.productId = productPrice.getProduct().getId();
        this.priceTypeName = productPrice.getPriceTypeName();
        this.price = productPrice.getPrice();
        this.discountRate = productPrice.getDiscountRate();
        this.createDate = productPrice.getCreateDate();
        this.modifyDate = productPrice.getModifyDate();
    }
}
