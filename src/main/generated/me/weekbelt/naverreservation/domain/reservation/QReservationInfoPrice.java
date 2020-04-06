package me.weekbelt.naverreservation.domain.reservationInfoPrice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationInfoPrice is a Querydsl query type for ReservationInfoPrice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservationInfoPrice extends EntityPathBase<ReservationInfoPrice> {

    private static final long serialVersionUID = 913875394L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationInfoPrice reservationInfoPrice = new QReservationInfoPrice("reservationInfoPrice");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final me.weekbelt.naverreservation.domain.product.QProductPrice productPrice;

    public final QReservationInfo reservationInfo;

    public QReservationInfoPrice(String variable) {
        this(ReservationInfoPrice.class, forVariable(variable), INITS);
    }

    public QReservationInfoPrice(Path<? extends ReservationInfoPrice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationInfoPrice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationInfoPrice(PathMetadata metadata, PathInits inits) {
        this(ReservationInfoPrice.class, metadata, inits);
    }

    public QReservationInfoPrice(Class<? extends ReservationInfoPrice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productPrice = inits.isInitialized("productPrice") ? new me.weekbelt.naverreservation.domain.product.QProductPrice(forProperty("productPrice"), inits.get("productPrice")) : null;
        this.reservationInfo = inits.isInitialized("reservationInfo") ? new QReservationInfo(forProperty("reservationInfo"), inits.get("reservationInfo")) : null;
    }

}

