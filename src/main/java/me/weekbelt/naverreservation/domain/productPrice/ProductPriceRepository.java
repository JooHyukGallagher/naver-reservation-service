package me.weekbelt.naverreservation.domain.productPrice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    List<ProductPrice> findProductPriceByProductIdOrderByIdDesc(Long productId);
}
