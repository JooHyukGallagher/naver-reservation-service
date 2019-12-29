package me.weekbelt.naverreservation.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductPrice is a Querydsl query type for ProductPrice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductPrice extends EntityPathBase<ProductPrice> {

    private static final long serialVersionUID = -2069937206L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductPrice productPrice = new QProductPrice("productPrice");

    public final me.weekbelt.naverreservation.domain.QBaseEntity _super = new me.weekbelt.naverreservation.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Double> discountRate = createNumber("discountRate", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath priceTypeName = createString("priceTypeName");

    public final QProduct product;

    public final ListPath<me.weekbelt.naverreservation.domain.reservation.ReservationInfoPrice, me.weekbelt.naverreservation.domain.reservation.QReservationInfoPrice> reservationInfoPrices = this.<me.weekbelt.naverreservation.domain.reservation.ReservationInfoPrice, me.weekbelt.naverreservation.domain.reservation.QReservationInfoPrice>createList("reservationInfoPrices", me.weekbelt.naverreservation.domain.reservation.ReservationInfoPrice.class, me.weekbelt.naverreservation.domain.reservation.QReservationInfoPrice.class, PathInits.DIRECT2);

    public QProductPrice(String variable) {
        this(ProductPrice.class, forVariable(variable), INITS);
    }

    public QProductPrice(Path<? extends ProductPrice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductPrice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductPrice(PathMetadata metadata, PathInits inits) {
        this(ProductPrice.class, metadata, inits);
    }

    public QProductPrice(Class<? extends ProductPrice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

