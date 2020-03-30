package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.promotion.PromotionRepository;
import me.weekbelt.naverreservation.web.dto.promotion.PromotionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public List<PromotionDto> findPromotionDto() {
        return promotionRepository.findPromotionDto();
    }
}
