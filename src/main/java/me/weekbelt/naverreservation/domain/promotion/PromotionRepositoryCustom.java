package me.weekbelt.naverreservation.domain.promotion;

import me.weekbelt.naverreservation.web.dto.promotion.PromotionDto;

import java.util.List;

public interface PromotionRepositoryCustom {
    List<PromotionDto> findPromotionDtos();
}
