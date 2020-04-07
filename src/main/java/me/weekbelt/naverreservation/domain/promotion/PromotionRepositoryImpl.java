package me.weekbelt.naverreservation.domain.promotion;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.web.dto.promotion.PromotionDto;

import javax.persistence.EntityManager;
import java.util.List;

import static me.weekbelt.naverreservation.domain.QFileInfo.*;
import static me.weekbelt.naverreservation.domain.product.QProduct.*;
import static me.weekbelt.naverreservation.domain.product.QProductImage.*;
import static me.weekbelt.naverreservation.domain.product.QPromotion.*;

@RequiredArgsConstructor
public class PromotionRepositoryImpl implements PromotionRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PromotionDto> findPromotionDto() {
        return queryFactory
                .select(Projections.fields(PromotionDto.class,
                        promotion.id,
                        promotion.product.id.as("productId"),
                        fileInfo.saveFileName.as("productImageUrl")
                        ))
                .from(promotion)
                .join(product).on(product.id.eq(promotion.product.id)).fetchJoin()
                .join(productImage).on(product.id.eq(productImage.product.id)).fetchJoin()
                .join(fileInfo).on(fileInfo.id.eq(productImage.fileInfo.id)).fetchJoin()
                .where(productImage.type.eq(ImageType.th))
                .fetch();
    }
}
