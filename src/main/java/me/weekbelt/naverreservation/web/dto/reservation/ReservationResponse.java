package me.weekbelt.naverreservation.web.dto.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
public class ReservationResponse {

    private Long reservationInfoId;
    private Long productId;
    private Long displayInfoId;

    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private boolean cancelYn;
    private String reservationDate;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    private List<ReservationPriceDto> prices;

    @Builder
    public ReservationResponse(Long reservationInfoId, Long productId, Long displayInfoId, String reservationName, String reservationTelephone, String reservationEmail, boolean cancelYn, String reservationDate, LocalDateTime createDate, LocalDateTime modifyDate, List<ReservationPriceDto> prices) {
        this.reservationInfoId = reservationInfoId;
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTelephone;
        this.reservationEmail = reservationEmail;
        this.cancelYn = cancelYn;
        this.reservationDate = reservationDate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.prices = prices;
    }
}
