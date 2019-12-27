package me.weekbelt.naverreservation.domain.reservation;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.BaseEntity;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.product.Product;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ReservationInfo extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "display_info_id")
    private DisplayInfo displayInfo;

    @OneToMany(mappedBy = "reservationInfo")
    private List<ReservationUserComment> reservationUserComments = new ArrayList<>();

    @OneToMany(mappedBy = "reservationInfo")
    private List<ReservationUserCommentImage> reservationUserCommentImages = new ArrayList<>();

    @Column(nullable = false)
    private String reservationName;

    @Column(nullable = false)
    private String reservationTel;

    @Column(nullable = false)
    private String reservationEmail;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean cancelFlag;
}
