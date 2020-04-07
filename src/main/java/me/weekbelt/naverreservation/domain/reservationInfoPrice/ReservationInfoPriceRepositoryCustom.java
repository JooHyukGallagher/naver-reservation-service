package me.weekbelt.naverreservation.domain.reservationInfoPrice;

import java.util.List;

public interface ReservationInfoPriceRepositoryCustom {
    List<ReservationInfoPrice> findReservationInfoPriceByReservationInfoId(Long reservationInfoId);
}
