package me.weekbelt.naverreservation.domain.reservationUserComment;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationUserCommentRepositoryCustom {
    List<ReservationUserComment> findReservationUserCommentsByProductId(Long productId);

    Double findAverageScoreByProductId(Long productId);
}
