package me.weekbelt.naverreservation.web.dto.display;

import lombok.Data;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
import me.weekbelt.naverreservation.web.dto.product.ProductImageDto;
import me.weekbelt.naverreservation.web.dto.product.ProductPriceDto;

import java.util.List;

@Data
public class DisplayInfoResponse {
    private DisplayInfoDto displayInfo;
    private List<ProductImageDto> productImages;
    private DisplayInfoImageDto displayInfoImage;
    private List<CommentDto> comments;
    private Double averageScore;
    private List<ProductPriceDto> productPrices;
}
