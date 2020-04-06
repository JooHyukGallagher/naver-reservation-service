package me.weekbelt.naverreservation.web.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.naverreservation.domain.reservationUserComment.ReservationUserComment;
import me.weekbelt.naverreservation.domain.reservationUserCommentImage.ReservationUserCommentImage;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
public class CommentResponse {

    private Long commentId;
    private Long productId;
    private Long reservationInfoId;

    private Double score;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    private CommentImageDto commentImage;

    public CommentResponse(ReservationUserComment reservationUserComment) {
        this.commentId = reservationUserComment.getId();
        this.productId = reservationUserComment.getProduct().getId();
        this.reservationInfoId = reservationUserComment.getReservationInfo().getId();

        this.score = reservationUserComment.getScore();
        this.comment = reservationUserComment.getComment();
        this.createDate = reservationUserComment.getCreateDate();
        this.modifyDate = reservationUserComment.getModifyDate();

        List<ReservationUserCommentImage> reservationUserCommentImage = reservationUserComment.getReservationUserCommentImages();
        if (!reservationUserCommentImage.isEmpty()) {
            this.commentImage = new CommentImageDto(reservationUserCommentImage.get(0));
        }
    }

}
