package me.weekbelt.naverreservation.domain.reservationInfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.category.QCategory;
import me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo;
import me.weekbelt.naverreservation.domain.product.QProduct;

import javax.persistence.EntityManager;
import java.util.List;

import static me.weekbelt.naverreservation.domain.category.QCategory.category;
import static me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo.displayInfo;
import static me.weekbelt.naverreservation.domain.product.QProduct.product;
import static me.weekbelt.naverreservation.domain.reservationInfo.QReservationInfo.*;

@RequiredArgsConstructor
public class ReservationInfoRepositoryImpl implements ReservationInfoRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReservationInfo> findReservationInfoByReservationEmail(String reservationEmail) {
        return queryFactory
                .selectFrom(reservationInfo)
                .join(reservationInfo.product, product).fetchJoin()
                .join(reservationInfo.displayInfo, displayInfo).fetchJoin()
                .join(product.category, category).fetchJoin()
                .where(reservationInfo.reservationEmail.eq(reservationEmail))
                .fetch();
    }
}
