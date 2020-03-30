package me.weekbelt.naverreservation.web.dto.promotion;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.web.dto.promotion.PromotionDto;

import java.util.List;

@Getter @Setter
public class PromotionResponse {

    List<PromotionDto> items;

    public PromotionResponse(List<PromotionDto> items) {
        this.items = items;
    }
}
