package me.weekbelt.naverreservation.web.dto.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationPriceDto {

    private Long reservationInfoPriceId;
    private Long productPriceId;
    private Long reservationInfoId;
    private Integer count;

    @Builder
    public ReservationPriceDto(Long reservationInfoPriceId, Long productPriceId, Long reservationInfoId, Integer count) {
        this.reservationInfoPriceId = reservationInfoPriceId;
        this.productPriceId = productPriceId;
        this.reservationInfoId = reservationInfoId;
        this.count = count;
    }

}
