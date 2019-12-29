package me.weekbelt.naverreservation.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationUserCommentRepository extends JpaRepository<ReservationUserComment, Long> {

    @Query("select ruc from ReservationUserComment ruc" +
            " join fetch ruc.reservationInfo ri" +
            " join fetch ruc.product p" +
            " where p.id = :productId order by ruc.id desc")
    List<ReservationUserComment> findReservationUserCommentByProductId(@Param("productId") Long productId);


}
