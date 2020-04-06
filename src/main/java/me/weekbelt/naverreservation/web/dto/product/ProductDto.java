package me.weekbelt.naverreservation.web.dto.product;

import lombok.*;
import me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo;

@NoArgsConstructor
@Getter @Setter
@ToString
public class ProductDto {

    // display_info 테이블
    private Long displayInfoId;
    private Long productId;
    private String placeName;

    // product 테이블
    private String productDescription;
    private String productContent;

    // file_info 테이블
    private String productImageUrl;

    public ProductDto(DisplayInfo displayInfo, String saveFileName) {
        this.displayInfoId = displayInfo.getId();
        this.productId = displayInfo.getProduct().getId();
        this.productDescription = displayInfo.getProduct().getDescription();
        this.placeName = displayInfo.getPlaceName();
        this.productContent = displayInfo.getProduct().getContent();
        this.productImageUrl = saveFileName;
    }

    @Builder
    public ProductDto(Long displayInfoId, Long productId, String placeName, String productDescription, String productContent, String productImageUrl) {
        this.displayInfoId = displayInfoId;
        this.productId = productId;
        this.placeName = placeName;
        this.productDescription = productDescription;
        this.productContent = productContent;
        this.productImageUrl = productImageUrl;
    }
}
