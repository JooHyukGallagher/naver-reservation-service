package me.weekbelt.naverreservation.domain.reservationInfoPrice;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static me.weekbelt.naverreservation.domain.product.QProductPrice.productPrice;
import static me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo.reservationInfo;
import static me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfoPrice.reservationInfoPrice;

@RequiredArgsConstructor
public class ReservationInfoPriceRepositoryImpl implements ReservationInfoPriceRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReservationInfoPrice> findReservationInfoPriceByReservationInfoId(Long reservationInfoId) {
        return queryFactory
                .selectFrom(reservationInfoPrice)
                .join(reservationInfoPrice.productPrice, productPrice).fetchJoin()
                .join(reservationInfoPrice.reservationInfo, reservationInfo).fetchJoin()
                .where(reservationInfoPrice.reservationInfo.id.eq(reservationInfoId))
                .fetch();
    }
}
