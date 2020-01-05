package me.weekbelt.naverreservation.domain.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.BaseTimeEntity;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.product.Product;
import me.weekbelt.naverreservation.util.TimeUtil;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationParam;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class ReservationInfo extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "display_info_id")
    private DisplayInfo displayInfo;

    @OneToMany(mappedBy = "reservationInfo", cascade = CascadeType.ALL)
    private List<ReservationUserComment> reservationUserComments = new ArrayList<>();

    @OneToMany(mappedBy = "reservationInfo", cascade = CascadeType.ALL)
    private List<ReservationUserCommentImage> reservationUserCommentImages = new ArrayList<>();

    @OneToMany(mappedBy = "reservationInfo", cascade = CascadeType.ALL)
    private List<ReservationInfoPrice> reservationInfoPrices = new ArrayList<>();

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

    @Builder
    public ReservationInfo(Product product, DisplayInfo displayInfo,
                           String reservationName, String reservationTel,
                           String reservationEmail, LocalDateTime reservationDate) {
        this.product = product;
        this.displayInfo = displayInfo;
        this.reservationName = reservationName;
        this.reservationTel = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
    }

    public static ReservationInfo createReservationInfo(ReservationParam reservationParam, Product product, DisplayInfo displayInfo) {
        return ReservationInfo.builder()
                .product(product)
                .displayInfo(displayInfo)
                .reservationName(reservationParam.getReservationName())
                .reservationTel(reservationParam.getReservationTelephone())
                .reservationEmail(reservationParam.getReservationEmail())
                .reservationDate(TimeUtil.convertStringToLocalDateTime(reservationParam.getReservationYearMonthDay()))
                .build();
    }
}
