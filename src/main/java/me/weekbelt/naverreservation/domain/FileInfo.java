package me.weekbelt.naverreservation.domain;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.display.DisplayInfoImage;
import me.weekbelt.naverreservation.domain.product.ProductImage;
import me.weekbelt.naverreservation.domain.reservation.ReservationUserCommentImage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class FileInfo extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "fileInfo")
    private List<DisplayInfoImage> displayInfoImages = new ArrayList<>();

    @OneToMany(mappedBy = "fileInfo")
    private List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "fileInfo")
    private List<ReservationUserCommentImage> reservationUserCommentImages = new ArrayList<>();

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, length = 4000)
    private String saveFileName;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private boolean deleteFlag;
}
