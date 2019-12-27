package me.weekbelt.naverreservation.web.dto.comment;

import lombok.Data;
import me.weekbelt.naverreservation.domain.reservation.ReservationInfo;
import me.weekbelt.naverreservation.domain.reservation.ReservationUserComment;
import me.weekbelt.naverreservation.domain.reservation.ReservationUserCommentImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentDto {

    // reservation_user_comment 테이블
    private Long commentId;
    private Long productId;
    private Long reservationInfoId;
    private Double score;
    private String comment;

    // reservation_info 테이블
    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private LocalDateTime reservationDate;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    private List<CommentImageDto> commentImages;

    public CommentDto(ReservationUserComment reservationUserComment) {
        this.commentId = reservationUserComment.getId();
        this.productId = reservationUserComment.getProduct().getId();
        this.score = reservationUserComment.getScore();
        this.comment = reservationUserComment.getComment();

        ReservationInfo reservationInfo = reservationUserComment.getReservationInfo();
        if (reservationInfo != null) {
            this.reservationInfoId = reservationInfo.getId();
            this.reservationName = reservationInfo.getReservationName();
            this.reservationTelephone = reservationInfo.getReservationTel();
            this.reservationEmail = reservationInfo.getReservationEmail();
            this.reservationDate = reservationInfo.getReservationDate();
            this.createDate = reservationInfo.getCreateDate();
            this.modifyDate = reservationInfo.getModifyDate();
        }

        List<ReservationUserCommentImage> reservationUserCommentImages = reservationUserComment.getReservationUserCommentImages();
        if (reservationUserCommentImages != null) {
            setReservationUserCommentImages(reservationUserCommentImages);
        }
    }

    private void setReservationUserCommentImages(List<ReservationUserCommentImage> reservationUserCommentImages) {
        List<CommentImageDto> commentImageDtos = new ArrayList<>();
        for (ReservationUserCommentImage reservationUserCommentImage : reservationUserCommentImages) {
            CommentImageDto commentImageDto = new CommentImageDto(reservationUserCommentImage);
            commentImageDtos.add(commentImageDto);
        }

        commentImages = commentImageDtos;
    }
}
