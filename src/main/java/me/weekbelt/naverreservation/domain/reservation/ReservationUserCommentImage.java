package me.weekbelt.naverreservation.domain.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.FileInfo;
import me.weekbelt.naverreservation.web.dto.comment.CommentResponse;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter @Setter
public class ReservationUserCommentImage {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_user_comment_id")
    private ReservationUserComment reservationUserComment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;

    @Builder
    public ReservationUserCommentImage(ReservationInfo reservationInfo,
                                       ReservationUserComment reservationUserComment,
                                       FileInfo fileInfo) {
        this.reservationInfo = reservationInfo;
        this.reservationUserComment = reservationUserComment;
        this.fileInfo = fileInfo;
    }

}
