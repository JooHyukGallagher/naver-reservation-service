package me.weekbelt.naverreservation.domain.productImage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.ImageType;

import javax.persistence.EntityManager;
import java.util.List;

import static me.weekbelt.naverreservation.domain.QFileInfo.fileInfo;
import static me.weekbelt.naverreservation.domain.product.QProduct.product;
import static me.weekbelt.naverreservation.domain.product.QProductImage.*;

@RequiredArgsConstructor
public class ProductImageRepositoryImpl implements ProductImageRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductImage> findProductImageByProductIdAndType(Long productId, ImageType type) {
        return queryFactory
                .selectFrom(productImage)
                .join(productImage.product, product).fetchJoin()
                .join(productImage.fileInfo, fileInfo).fetchJoin()
                .where(productImage.product.id.eq(productId), productImage.type.eq(type))
                .fetch();
    }
}
