package me.weekbelt.naverreservation.domain.promotion;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.product.Product;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Promotion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
