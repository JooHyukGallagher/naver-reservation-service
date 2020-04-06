package me.weekbelt.naverreservation.domain.reservationUserComment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.product.QProduct;
import me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo;
import me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationUserComment;

import javax.persistence.EntityManager;
import java.util.List;

import static me.weekbelt.naverreservation.domain.product.QProduct.*;
import static me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo.*;
import static me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationUserComment.*;

@RequiredArgsConstructor
public class ReservationUserCommentRepositoryImpl implements ReservationUserCommentRepositoryCustom{

    private final EntityManager em;

    private JPAQueryFactory queryFactory;

    @Override
    public List<ReservationUserComment> findReservationUserCommentByProductId(Long productId) {
        queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(reservationUserComment)
                .join(reservationUserComment.reservationInfo, reservationInfo).fetchJoin()
                .join(reservationUserComment.product, product)
                .where(reservationUserComment.product.id.eq(productId))
                .orderBy(reservationUserComment.id.desc())
                .fetch();
    }
}
