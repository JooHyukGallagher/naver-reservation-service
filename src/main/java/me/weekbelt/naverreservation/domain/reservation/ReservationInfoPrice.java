package me.weekbelt.naverreservation.domain.reservation;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.product.ProductPrice;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ReservationInfoPrice {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    private int count;
}
