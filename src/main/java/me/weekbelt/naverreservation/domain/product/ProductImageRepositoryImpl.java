package me.weekbelt.naverreservation.domain.product;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.ImageType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductImageRepositoryImpl {

    private final EntityManager em;

    public List<ProductImage> getProductImageByProductId(Long productId, ImageType type){
        return em.createQuery(
                "select pi from ProductImage pi" +
                        " join fetch pi.fileInfo fi" +
                        " join fetch pi.product p" +
                        " where p.id = :productId and pi.type = :type")
                .setParameter("productId", productId)
                .setParameter("type", type)
                .getResultList();
    }
}
