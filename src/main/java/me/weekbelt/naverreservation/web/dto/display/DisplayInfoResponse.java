package me.weekbelt.naverreservation.web.dto.display;

import lombok.*;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
import me.weekbelt.naverreservation.web.dto.product.ProductImageDto;
import me.weekbelt.naverreservation.web.dto.product.ProductPriceDto;

import java.util.List;


@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class DisplayInfoResponse {
    private DisplayInfoDto displayInfo;
    private List<ProductImageDto> productImages;
    private DisplayInfoImageDto displayInfoImage;
    private List<CommentDto> comments;
    private Double averageScore;
    private List<ProductPriceDto> productPrices;
}
