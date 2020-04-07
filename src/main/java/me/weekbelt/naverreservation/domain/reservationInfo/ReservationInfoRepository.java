package me.weekbelt.naverreservation.domain.reservationInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long>, ReservationInfoRepositoryCustom {
    @Query("select ri from ReservationInfo ri" +
            " join fetch ri.product p" +
            " join fetch ri.displayInfo di" +
            " join fetch p.category c" +
            " where ri.id = :reservationInfoId")
    ReservationInfo findReservationInfoByReservationInfoId(@Param("reservationInfoId") Long reservationInfoId);
}
