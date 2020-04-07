package me.weekbelt.naverreservation.domain.reservationInfoPrice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationInfoPriceRepository extends JpaRepository<ReservationInfoPrice, Long>, ReservationInfoPriceRepositoryCustom {
}
