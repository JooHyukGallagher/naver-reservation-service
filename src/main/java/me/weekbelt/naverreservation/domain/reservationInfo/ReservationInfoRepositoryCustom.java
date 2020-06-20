package me.weekbelt.naverreservation.domain.reservationInfo;

import java.util.List;

public interface ReservationInfoRepositoryCustom {
    List<ReservationInfo> findReservationInfoByReservationEmail(String reservationEmail);

    ReservationInfo findReservationInfoByReservationInfoId(Long reservationInfoId);
}
