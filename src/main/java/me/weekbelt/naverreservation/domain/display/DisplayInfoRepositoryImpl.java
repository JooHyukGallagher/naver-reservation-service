package me.weekbelt.naverreservation.domain.display;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static me.weekbelt.naverreservation.domain.category.QCategory.category;
import static me.weekbelt.naverreservation.domain.display.QDisplayInfo.*;
import static me.weekbelt.naverreservation.domain.product.QProduct.product;

@RequiredArgsConstructor
@Repository
public class DisplayInfoRepositoryImpl implements DisplayInfoRepositoryCustom {

    private final EntityManager em;

    private JPAQueryFactory queryFactory;

    @Override
    public List<DisplayInfo> findDisplayInfoWithProductByCategoryId(Long categoryId, Integer start, Integer limit) {
        queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(displayInfo)
                .join(displayInfo.product, product).fetchJoin()
                .join(product.category, category).fetchJoin()
                .where(categoryIdEq(categoryId))
                .limit(limit)
                .offset(start)
                .fetch();
    }

    @Override
    public Integer countDisplayInfoNumberByCategoryId(Long categoryId) {
        queryFactory = new JPAQueryFactory(em);

        return (int) queryFactory
                .selectFrom(displayInfo)
                .join(displayInfo.product, product)
                .join(product.category, category)
                .where(categoryIdEq(categoryId))
                .fetchCount();
    }

    private BooleanExpression categoryIdEq(Long categoryIdCond){
        if (categoryIdCond == null || categoryIdCond == 0){
            return null;
        }
        return product.category.id.eq(categoryIdCond);
    }
}
