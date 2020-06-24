package me.weekbelt.naverreservation.domain.reservationUserCommentImage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.fileInfo.FileInfo;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfo;
import me.weekbelt.naverreservation.domain.reservationUserComment.ReservationUserComment;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter @Setter
public class ReservationUserCommentImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
