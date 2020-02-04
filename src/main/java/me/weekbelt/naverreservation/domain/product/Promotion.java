package me.weekbelt.naverreservation.domain.product;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.web.dto.product.PromotionDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
public class Promotion {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
