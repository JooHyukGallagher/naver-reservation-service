package me.weekbelt.naverreservation.domain.reservation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationUserComment is a Querydsl query type for ReservationUserComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservationUserComment extends EntityPathBase<ReservationUserComment> {

    private static final long serialVersionUID = 701190875L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationUserComment reservationUserComment = new QReservationUserComment("reservationUserComment");

    public final me.weekbelt.naverreservation.domain.QBaseEntity _super = new me.weekbelt.naverreservation.domain.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final me.weekbelt.naverreservation.domain.product.QProduct product;

    public final QReservationInfo reservationInfo;

    public final ListPath<ReservationUserCommentImage, QReservationUserCommentImage> reservationUserCommentImages = this.<ReservationUserCommentImage, QReservationUserCommentImage>createList("reservationUserCommentImages", ReservationUserCommentImage.class, QReservationUserCommentImage.class, PathInits.DIRECT2);

    public final NumberPath<Double> score = createNumber("score", Double.class);

    public QReservationUserComment(String variable) {
        this(ReservationUserComment.class, forVariable(variable), INITS);
    }

    public QReservationUserComment(Path<? extends ReservationUserComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationUserComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationUserComment(PathMetadata metadata, PathInits inits) {
        this(ReservationUserComment.class, metadata, inits);
    }

    public QReservationUserComment(Class<? extends ReservationUserComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new me.weekbelt.naverreservation.domain.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.reservationInfo = inits.isInitialized("reservationInfo") ? new QReservationInfo(forProperty("reservationInfo"), inits.get("reservationInfo")) : null;
    }

}

