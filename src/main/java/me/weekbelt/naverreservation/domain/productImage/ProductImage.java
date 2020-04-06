package me.weekbelt.naverreservation.domain.productImage;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.FileInfo;
import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.domain.product.Product;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ProductImage {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImageType type;

    // == 비즈니스 로직 == //
    public String getSaveFileName() {
        return this.getFileInfo().getSaveFileName();
    }
}
