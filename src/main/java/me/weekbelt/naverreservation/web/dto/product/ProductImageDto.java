package me.weekbelt.naverreservation.web.dto.product;

import lombok.Data;
import me.weekbelt.naverreservation.domain.FileInfo;
import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.domain.productImage.ProductImage;

import java.time.LocalDateTime;

@Data
public class ProductImageDto {

    // product_image 테이블
    private Long productImageId;
    private Long productId;
    private ImageType type;

    // file_info 테이블
    private Long fileInfoId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private boolean deleteFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ProductImageDto(ProductImage productImage) {
        this.productImageId = productImage.getId();
        this.productId = productImage.getProduct().getId();
        this.type = productImage.getType();

        FileInfo fileInfo = productImage.getFileInfo();
        if (fileInfo != null) {
            this.fileInfoId = fileInfo.getId();
            this.fileName = fileInfo.getFileName();
            this.saveFileName = fileInfo.getSaveFileName();
            this.contentType = fileInfo.getContentType();
            this.deleteFlag = fileInfo.isDeleteFlag();
            this.createDate = fileInfo.getCreateDate();
            this.modifyDate = fileInfo.getModifyDate();
        }
    }
}
