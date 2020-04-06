package me.weekbelt.naverreservation.web.dto.display;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.naverreservation.domain.category.Category;
import me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo;
import me.weekbelt.naverreservation.domain.product.Product;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class DisplayInfoDto {

    // display_info 테이블
    private Long displayInfoId;
    private String openingHours;
    private String placeName;
    private String placeLot;
    private String placeStreet;
    private String telephone;
    private String homepage;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    // product 테이블
    private Long productId;
    private String productDescription;
    private String productContent;
    private String productEvent;

    // category 테이블
    private Long categoryId;
    private String categoryName;

    public DisplayInfoDto(DisplayInfo displayInfo) {
        this.displayInfoId = displayInfo.getId();
        this.openingHours = displayInfo.getOpeningHours();
        this.placeName = displayInfo.getPlaceName();
        this.placeLot = displayInfo.getPlaceLot();
        this.placeStreet = displayInfo.getPlaceStreet();
        this.telephone = displayInfo.getTel();
        this.homepage = displayInfo.getHomepage();
        this.email = displayInfo.getEmail();
        this.createDate = displayInfo.getCreateDate();
        this.modifyDate = displayInfo.getModifyDate();

        Product product = displayInfo.getProduct();
        if (product != null) {
            this.productId = product.getId();
            this.productDescription = product.getDescription();
            this.productContent = product.getContent();
            this.productEvent = product.getEvent();

        }

        Category category = displayInfo.getProduct().getCategory();
        if (category != null) {
            this.categoryId = category.getId();
            this.categoryName = category.getName();
        }
    }
}
