package me.weekbelt.naverreservation.domain.reservationUserComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationUserCommentRepository extends JpaRepository<ReservationUserComment, Long>, ReservationUserCommentRepositoryCustom {

    @Query("select ruc from ReservationUserComment ruc" +
            " join fetch ruc.product p" +
            " join fetch ruc.reservationInfo ri" +
            " where ruc.id = :commentId")
    Optional<ReservationUserComment> findReservationUserCommentByCommentId(@Param("commentId") Long commentId);
}
