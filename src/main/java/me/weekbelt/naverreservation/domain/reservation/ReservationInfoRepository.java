package me.weekbelt.naverreservation.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long> {

    @Query("select ri from ReservationInfo ri" +
            " join fetch ri.product p" +
            " join fetch ri.displayInfo di" +
            " join fetch p.category c" +
            " where ri.reservationEmail = :reservationEmail")
    List<ReservationInfo> findReservationInfoByReservationEmail(@Param("reservationEmail") String reservationEmail);

    @Query("select ri from ReservationInfo ri" +
            " join fetch ri.product p" +
            " join fetch ri.displayInfo di" +
            " join fetch p.category c" +
            " where ri.id = :reservationInfoId")
    ReservationInfo findReservationInfoByReservationInfoId(@Param("reservationInfoId") Long reservationInfoId);
}
