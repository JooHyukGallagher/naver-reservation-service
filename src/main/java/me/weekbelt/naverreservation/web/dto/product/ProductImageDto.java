package me.weekbelt.naverreservation.web.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.weekbelt.naverreservation.domain.fileInfo.FileInfo;
import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.domain.productImage.ProductImage;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
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
