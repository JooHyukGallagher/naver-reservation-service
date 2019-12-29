package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.service.DisplayInfoService;
import me.weekbelt.naverreservation.service.ProductService;
import me.weekbelt.naverreservation.service.ReservationService;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoDto;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoImageDto;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoResponse;
import me.weekbelt.naverreservation.web.dto.product.ProductDto;
import me.weekbelt.naverreservation.web.dto.product.ProductImageDto;
import me.weekbelt.naverreservation.web.dto.product.ProductPriceDto;
import me.weekbelt.naverreservation.web.dto.product.ProductResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductApiController {

    private final ProductService productService;
    private final DisplayInfoService displayInfoService;
    private final ReservationService reservationService;

    @GetMapping("/products")
    public ProductResponse getProductList(@RequestParam(defaultValue = "0") Long categoryId,
                                          @RequestParam(defaultValue = "0") Integer start){

        List<ProductDto> productDtos = productService.findDisplayInfoWithProduct(categoryId, start);
        Integer totalCount = productService.countProductNumByCategoryId(categoryId);

        return ProductResponse.builder()
                .items(productDtos)
                .totalCount(totalCount)
                .build();
    }

    @GetMapping("/products/{displayInfoId}")
    public DisplayInfoResponse getDisplayInfoResponse(@PathVariable Long displayInfoId) {
        DisplayInfoDto displayInfo = displayInfoService.getDisplayInfoDto(displayInfoId);
        DisplayInfoImageDto displayInfoImage = displayInfoService.findDisplayInfoImageDtoByDisplayInfoId(displayInfoId);

        Long productId = displayInfo.getProductId();
        List<ProductImageDto> productImages = productService.findProductImageDtosByProductId(productId, ImageType.ma);
        List<CommentDto> comments = reservationService.findCommentDtosByProductId(productId);
        List<ProductPriceDto> productPrices = productService.findProductPriceDtoByProductId(productId);
        Double averageScore = reservationService.findAverageScoreByProductId(productId);

        return DisplayInfoResponse.builder()
                .displayInfo(displayInfo)
                .displayInfoImage(displayInfoImage)
                .productImages(productImages)
                .comments(comments)
                .productPrices(productPrices)
                .averageScore(averageScore)
                .build();
    }
}
