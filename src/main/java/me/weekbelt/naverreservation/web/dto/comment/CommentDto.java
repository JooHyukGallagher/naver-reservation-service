package me.weekbelt.naverreservation.web.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfo;
import me.weekbelt.naverreservation.domain.reservationUserComment.ReservationUserComment;
import me.weekbelt.naverreservation.domain.reservationUserCommentImage.ReservationUserCommentImage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Getter
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

    // reservation_info_image 테이블
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
            this.commentImages = setReservationUserCommentImages(reservationUserCommentImages);
        }
    }

    private List<CommentImageDto> setReservationUserCommentImages(List<ReservationUserCommentImage> reservationUserCommentImages) {

        return reservationUserCommentImages.stream()
                .map(CommentImageDto::new)
                .collect(Collectors.toList());
    }
}
