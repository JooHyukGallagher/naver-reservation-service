package me.weekbelt.naverreservation.domain.category;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.web.dto.category.CategoryDto;

import javax.persistence.EntityManager;
import java.util.List;

import static me.weekbelt.naverreservation.domain.category.QCategory.*;
import static me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo.*;
import static me.weekbelt.naverreservation.domain.product.QProduct.*;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CategoryDto> findCategoryDtos() {
        return queryFactory
                .select(Projections.fields(CategoryDto.class,
                        category.id.as("categoryId"),
                        category.name,
                        category.name.count().intValue().as("count")
                ))
                .from(category)
                .join(product).on(category.id.eq(product.category.id)).fetchJoin()
                .join(displayInfo).on(product.id.eq(displayInfo.product.id)).fetchJoin()
                .groupBy(category.name, category.id)
                .orderBy(category.id.asc())
                .fetch();
    }
}
