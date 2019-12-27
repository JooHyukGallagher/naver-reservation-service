package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.service.ProductService;
import me.weekbelt.naverreservation.web.dto.product.ProductDto;
import me.weekbelt.naverreservation.web.dto.product.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductApiController {

    private final ProductService productService;

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
}
