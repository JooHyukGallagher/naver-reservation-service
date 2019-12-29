package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.service.ProductService;
import me.weekbelt.naverreservation.web.dto.product.PromotionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PromotionApiController {

    private final ProductService productService;

    @GetMapping("/promotions")
    private PromotionResponse getPromotionResponse(){
        return new PromotionResponse(productService.findPromotionDto());
    }
}
