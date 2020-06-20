package me.weekbelt.naverreservation.domain.reservationInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long>, ReservationInfoRepositoryCustom {
}
