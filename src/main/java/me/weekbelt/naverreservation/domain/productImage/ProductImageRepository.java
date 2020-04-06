package me.weekbelt.naverreservation.domain.productImage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, ProductImageRepositoryCustom {

//    @Query("select pi from ProductImage pi" +
//            " join fetch pi.fileInfo fi" +
//            " join fetch pi.product p" +
//            " where p.id = :productId and pi.type = :type")
//    List<ProductImage> findProductImageByProductIdAndType(@Param("productId") Long productId,
//                                                          @Param("type") ImageType type);
}
