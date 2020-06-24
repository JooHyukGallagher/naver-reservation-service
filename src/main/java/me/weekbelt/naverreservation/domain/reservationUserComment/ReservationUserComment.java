package me.weekbelt.naverreservation.domain.reservationUserComment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.BaseTimeEntity;
import me.weekbelt.naverreservation.domain.product.Product;
import me.weekbelt.naverreservation.domain.reservationUserCommentImage.ReservationUserCommentImage;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter @Setter
public class ReservationUserComment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    @OneToMany(mappedBy = "reservationUserComment")
    private List<ReservationUserCommentImage> reservationUserCommentImages = new ArrayList<>();

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private String comment;

    @Builder
    public ReservationUserComment(Product product, ReservationInfo reservationInfo, double score, String comment) {
        this.product = product;
        this.reservationInfo = reservationInfo;
        this.score = score;
        this.comment = comment;
    }


}
