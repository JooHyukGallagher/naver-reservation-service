package me.weekbelt.naverreservation.domain.product;

import me.weekbelt.naverreservation.domain.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, ProductImageRepositoryCustom {

//    @Query("select pi from ProductImage pi" +
//            " join fetch pi.fileInfo fi" +
//            " join fetch pi.product p" +
//            " where p.id = :productId and pi.type = :type")
//    List<ProductImage> findProductImageByProductIdAndType(@Param("productId") Long productId,
//                                                          @Param("type") ImageType type);
}
