package me.weekbelt.naverreservation.domain.productPrice;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.BaseTimeEntity;
import me.weekbelt.naverreservation.domain.product.Product;
import me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationInfoPrice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ProductPrice extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "productPrice")
    private List<ReservationInfoPrice> reservationInfoPrices = new ArrayList<>();

    @Column(nullable = false, length = 25)
    private String priceTypeName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Double discountRate;
}
