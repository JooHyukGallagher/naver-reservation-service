package me.weekbelt.naverreservation.domain.reservationInfoPrice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationInfo is a Querydsl query type for ReservationInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservationInfo extends EntityPathBase<ReservationInfo> {

    private static final long serialVersionUID = -2009177145L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationInfo reservationInfo = new QReservationInfo("reservationInfo");

    public final me.weekbelt.naverreservation.domain.QBaseEntity _super = new me.weekbelt.naverreservation.domain.QBaseEntity(this);

    public final BooleanPath cancelFlag = createBoolean("cancelFlag");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo displayInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final me.weekbelt.naverreservation.domain.product.QProduct product;

    public final DateTimePath<java.time.LocalDateTime> reservationDate = createDateTime("reservationDate", java.time.LocalDateTime.class);

    public final StringPath reservationEmail = createString("reservationEmail");

    public final StringPath reservationName = createString("reservationName");

    public final StringPath reservationTel = createString("reservationTel");

    public final ListPath<ReservationUserCommentImage, QReservationUserCommentImage> reservationUserCommentImages = this.<ReservationUserCommentImage, QReservationUserCommentImage>createList("reservationUserCommentImages", ReservationUserCommentImage.class, QReservationUserCommentImage.class, PathInits.DIRECT2);

    public final ListPath<ReservationUserComment, QReservationUserComment> reservationUserComments = this.<ReservationUserComment, QReservationUserComment>createList("reservationUserComments", ReservationUserComment.class, QReservationUserComment.class, PathInits.DIRECT2);

    public QReservationInfo(String variable) {
        this(ReservationInfo.class, forVariable(variable), INITS);
    }

    public QReservationInfo(Path<? extends ReservationInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationInfo(PathMetadata metadata, PathInits inits) {
        this(ReservationInfo.class, metadata, inits);
    }

    public QReservationInfo(Class<? extends ReservationInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.displayInfo = inits.isInitialized("displayInfo") ? new me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo(forProperty("displayInfo"), inits.get("displayInfo")) : null;
        this.product = inits.isInitialized("product") ? new me.weekbelt.naverreservation.domain.product.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

