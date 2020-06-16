package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.promotion.PromotionRepository;
import me.weekbelt.naverreservation.web.dto.promotion.PromotionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PromotionApiController {

    private final PromotionRepository promotionRepository;

    @GetMapping("/promotions")
    private PromotionResponse getPromotionResponse(){
        return PromotionResponse.builder()
                .items(promotionRepository.findPromotionDtos())
                .build();
    }
}
