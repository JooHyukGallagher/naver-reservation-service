package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.reservation.ReservationUserComment;
import me.weekbelt.naverreservation.domain.reservation.ReservationUserCommentRepository;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationUserCommentRepository reservationUserCommentRepository;

    public List<CommentDto> findCommentDto(Long productId) {
        List<ReservationUserComment> reservationUserComments = reservationUserCommentRepository.findReservationUserCommentByProductId(productId);

        List<CommentDto> commentDtos = new ArrayList<>();
        for (ReservationUserComment reservationUserComment : reservationUserComments) {
            CommentDto commentDto = new CommentDto(reservationUserComment);
            commentDtos.add(commentDto);
        }

        return commentDtos;
    }

    public Double findAverageScore(Long productId) {
        double averageScore = 0.0;

        List<ReservationUserComment> reservationUserComments = reservationUserCommentRepository.findReservationUserCommentByProductId(productId);
        if (!reservationUserComments.isEmpty()){
            for (ReservationUserComment reservationUserComment : reservationUserComments) {
                averageScore += reservationUserComment.getScore();
            }
            averageScore /= reservationUserComments.size();
        }

        return averageScore;
    }

}
