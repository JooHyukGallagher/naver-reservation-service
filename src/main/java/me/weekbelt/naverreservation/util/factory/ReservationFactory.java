package me.weekbelt.naverreservation.util.factory;

import me.weekbelt.naverreservation.domain.productPrice.ProductPrice;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfo;
import me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationInfoPrice;
import me.weekbelt.naverreservation.util.TimeUtil;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationPriceDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationResponse;

import java.util.List;

public class ReservationFactory {

    public static ReservationResponse getReservationResponse(ReservationInfo reservationInfo,
                                                             List<ReservationPriceDto> reservationPriceDtos) {
        return ReservationResponse.builder()
                .reservationInfoId(reservationInfo.getId())
                .productId(reservationInfo.getProduct().getId())
                .displayInfoId(reservationInfo.getDisplayInfo().getId())
                .reservationName(reservationInfo.getReservationName())
                .reservationTelephone(reservationInfo.getReservationTel())
                .reservationEmail(reservationInfo.getReservationEmail())
                .cancelYn(reservationInfo.isCancelFlag())
                .reservationDate(TimeUtil.convertLocalDateTimeToString(reservationInfo.getReservationDate()))
                .createDate(reservationInfo.getCreateDate())
                .modifyDate(reservationInfo.getModifyDate())
                .prices(reservationPriceDtos)
                .build();
    }

    public static ReservationPriceDto getReservationPriceDto(ReservationInfoPrice reservationInfoPrice) {
        return ReservationPriceDto.builder()
                .reservationInfoPriceId(reservationInfoPrice.getId())
                .reservationInfoId(reservationInfoPrice.getReservationInfo().getId())
                .productPriceId(reservationInfoPrice.getProductPrice().getId())
                .count(reservationInfoPrice.getCount())
                .build();
    }

    public static ReservationInfoPrice getReservationInfoPrice(ReservationInfo reservationInfo,
                                                               ReservationPriceDto reservationPriceDto,
                                                               ProductPrice productPrice) {
        return ReservationInfoPrice.builder()
                .reservationInfo(reservationInfo)
                .productPrice(productPrice)
                .count(reservationPriceDto.getCount())
                .build();
    }

}
