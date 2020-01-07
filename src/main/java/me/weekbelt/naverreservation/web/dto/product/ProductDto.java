package me.weekbelt.naverreservation.web.dto.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;

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
}
