package me.weekbelt.naverreservation.domain.promotion;

import me.weekbelt.naverreservation.domain.promotion.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long>, PromotionRepositoryCustom {
}
