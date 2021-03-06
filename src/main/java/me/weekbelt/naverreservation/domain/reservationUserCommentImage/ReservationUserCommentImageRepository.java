package me.weekbelt.naverreservation.domain.reservationUserCommentImage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationUserCommentImageRepository extends JpaRepository<ReservationUserCommentImage, Long> {
    Optional<ReservationUserCommentImage> findByReservationUserCommentId(Long reservationUserCommentId);

}
