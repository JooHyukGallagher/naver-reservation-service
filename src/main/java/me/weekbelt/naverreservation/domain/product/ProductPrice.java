package me.weekbelt.naverreservation.domain.product;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.BaseEntity;
import me.weekbelt.naverreservation.domain.reservation.ReservationInfoPrice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ProductPrice extends BaseEntity {
    @Id @GeneratedValue
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
