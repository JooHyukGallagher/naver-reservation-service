package me.weekbelt.naverreservation.domain.product;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.BaseTimeEntity;
import me.weekbelt.naverreservation.domain.category.Category;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.promotion.Promotion;
import me.weekbelt.naverreservation.domain.reservation.ReservationInfo;
import me.weekbelt.naverreservation.domain.reservation.ReservationUserComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Product extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Promotion> promotions = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<DisplayInfo> displayInfos = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductPrice> productPrices = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ReservationInfo> reservationInfos = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ReservationUserComment> reservationUserComments = new ArrayList<>();

    private String description;

    @Lob
    private String content;

    private String event;

    // == 비즈니스 로직 == //

}
