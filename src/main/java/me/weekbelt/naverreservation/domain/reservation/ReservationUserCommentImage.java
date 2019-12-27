package me.weekbelt.naverreservation.domain.reservation;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.FileInfo;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ReservationUserCommentImage {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_user_comment_id")
    private ReservationUserComment reservationUserComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;
}
