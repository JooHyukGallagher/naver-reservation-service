package me.weekbelt.naverreservation.web.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PromotionResponse {

    List<PromotionDto> items;
}
