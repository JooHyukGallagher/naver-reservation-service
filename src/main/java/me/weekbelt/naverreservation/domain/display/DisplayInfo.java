package me.weekbelt.naverreservation.domain.display;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.BaseTimeEntity;
import me.weekbelt.naverreservation.domain.product.Product;
import me.weekbelt.naverreservation.domain.reservation.ReservationInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class DisplayInfo extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "displayInfo")
    private List<ReservationInfo> reservationInfos = new ArrayList<>();

    private String openingHours;

    @Column(nullable = false)
    private String placeName;

    private String placeLot;
    private String placeStreet;
    private String tel;
    private String homepage;
    private String email;
}
