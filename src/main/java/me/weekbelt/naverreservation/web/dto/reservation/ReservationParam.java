package me.weekbelt.naverreservation.web.dto.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ReservationParam {

    private Long displayInfoId;
    private Long productId;

    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private String reservationYearMonthDay;

    private List<ReservationPriceDto> prices;

    @Builder
    public ReservationParam(Long displayInfoId, Long productId, String reservationName, String reservationTelephone, String reservationEmail, String reservationYearMonthDay, List<ReservationPriceDto> prices) {
        this.displayInfoId = displayInfoId;
        this.productId = productId;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTelephone;
        this.reservationEmail = reservationEmail;
        this.reservationYearMonthDay = reservationYearMonthDay;
        this.prices = prices;
    }

}
