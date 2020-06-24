package me.weekbelt.naverreservation.domain.fileInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.naverreservation.domain.BaseTimeEntity;
import me.weekbelt.naverreservation.domain.displayInfoImage.DisplayInfoImage;
import me.weekbelt.naverreservation.domain.productImage.ProductImage;
import me.weekbelt.naverreservation.domain.reservationUserCommentImage.ReservationUserCommentImage;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class FileInfo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ColumnDefault("false")
    private boolean deleteFlag;

    @Builder
    public FileInfo(String fileName, String saveFileName, String contentType, boolean deleteFlag) {
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.contentType = contentType;
        this.deleteFlag = deleteFlag;
    }
}
