package me.weekbelt.naverreservation.domain.reservationInfoPrice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationUserCommentImage is a Querydsl query type for ReservationUserCommentImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservationUserCommentImage extends EntityPathBase<ReservationUserCommentImage> {

    private static final long serialVersionUID = -1536886976L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationUserCommentImage reservationUserCommentImage = new QReservationUserCommentImage("reservationUserCommentImage");

    public final me.weekbelt.naverreservation.domain.QFileInfo fileInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReservationInfo reservationInfo;

    public final QReservationUserComment reservationUserComment;

    public QReservationUserCommentImage(String variable) {
        this(ReservationUserCommentImage.class, forVariable(variable), INITS);
    }

    public QReservationUserCommentImage(Path<? extends ReservationUserCommentImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationUserCommentImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationUserCommentImage(PathMetadata metadata, PathInits inits) {
        this(ReservationUserCommentImage.class, metadata, inits);
    }

    public QReservationUserCommentImage(Class<? extends ReservationUserCommentImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileInfo = inits.isInitialized("fileInfo") ? new me.weekbelt.naverreservation.domain.QFileInfo(forProperty("fileInfo")) : null;
        this.reservationInfo = inits.isInitialized("reservationInfo") ? new QReservationInfo(forProperty("reservationInfo"), inits.get("reservationInfo")) : null;
        this.reservationUserComment = inits.isInitialized("reservationUserComment") ? new QReservationUserComment(forProperty("reservationUserComment"), inits.get("reservationUserComment")) : null;
    }

}

