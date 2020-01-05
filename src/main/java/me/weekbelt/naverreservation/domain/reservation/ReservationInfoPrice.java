package me.weekbelt.naverreservation.domain.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.product.ProductPrice;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationPriceDto;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
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

    @Builder
    public ReservationInfoPrice(ProductPrice productPrice, ReservationInfo reservationInfo, Integer count) {
        this.productPrice = productPrice;
        this.reservationInfo = reservationInfo;
        this.count = count;
    }

    //== 연관관계 메서드==//
    public void setReservationInfo(ReservationInfo reservationInfo){
        this.reservationInfo = reservationInfo;
        reservationInfo.getReservationInfoPrices().add(this);
    }
}
