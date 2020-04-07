package me.weekbelt.naverreservation.web.dto.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfo;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoDto;

import java.time.LocalDateTime;


@Getter
public class ReservationInfoDto {
    private Long reservationInfoId;
    private Long productId;
    private Long displayInfoId;

    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private boolean cancelYn;
    private LocalDateTime reservationDate;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    private DisplayInfoDto displayInfo;

    private Integer totalPrice;

    public ReservationInfoDto(ReservationInfo reservationInfo) {
        this.reservationInfoId = reservationInfo.getId();
        this.productId = reservationInfo.getProduct().getId();
        this.displayInfoId = reservationInfo.getDisplayInfo().getId();
        this.reservationName = reservationInfo.getReservationName();
        this.reservationTelephone = reservationInfo.getReservationTel();
        this.reservationEmail = reservationInfo.getReservationEmail();
        this.cancelYn = reservationInfo.isCancelFlag();
        this.reservationDate = reservationInfo.getReservationDate();
        this.createDate = reservationInfo.getCreateDate();
        this.modifyDate = reservationInfo.getModifyDate();
        this.displayInfo = new DisplayInfoDto(reservationInfo.getDisplayInfo());
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

}
