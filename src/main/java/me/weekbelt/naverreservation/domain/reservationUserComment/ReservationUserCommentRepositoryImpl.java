package me.weekbelt.naverreservation.domain.reservationUserComment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.reservationInfo.QReservationInfo;

import javax.persistence.EntityManager;
import java.util.List;

import static me.weekbelt.naverreservation.domain.product.QProduct.*;
import static me.weekbelt.naverreservation.domain.reservationInfo.QReservationInfo.*;
import static me.weekbelt.naverreservation.domain.reservationUserComment.QReservationUserComment.reservationUserComment;

@RequiredArgsConstructor
public class ReservationUserCommentRepositoryImpl implements ReservationUserCommentRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReservationUserComment> findReservationUserCommentsByProductId(Long productId) {
        return queryFactory
                .selectFrom(reservationUserComment)
                .join(reservationUserComment.reservationInfo, reservationInfo).fetchJoin()
                .join(reservationUserComment.product, product)
                .where(reservationUserComment.product.id.eq(productId))
                .orderBy(reservationUserComment.id.desc())
                .fetch();
    }

    @Override
    public Double findAverageScoreByProductId(Long productId) {
        return queryFactory
                .select(reservationUserComment.score.avg())
                .from(reservationUserComment)
                .join(reservationUserComment.reservationInfo, reservationInfo)
                .join(reservationUserComment.product, product)
                .where(reservationUserComment.product.id.eq(productId))
                .fetchOne();
    }
}
