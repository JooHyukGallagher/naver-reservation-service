package me.weekbelt.naverreservation.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ReservationUserCommentRepository extends JpaRepository<ReservationUserComment, Long> {

    @Query("select ruc from ReservationUserComment ruc" +
            " join fetch ruc.reservationInfo ri" +
            " join fetch ruc.product p" +
            " where p.id = :productId order by ruc.id desc")
    List<ReservationUserComment> findReservationUserCommentByProductId(@Param("productId") Long productId);


    @Query("select ruc from ReservationUserComment ruc" +
            " join fetch ruc.product p" +
            " join fetch ruc.reservationInfo ri" +
            " where ruc.id = :commentId")
    Optional<ReservationUserComment> findReservationUserCommentByCommentId(@Param("commentId") Long commentId);
}
