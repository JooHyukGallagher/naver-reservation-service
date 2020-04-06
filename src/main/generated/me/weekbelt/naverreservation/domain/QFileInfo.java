package me.weekbelt.naverreservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFileInfo is a Querydsl query type for FileInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileInfo extends EntityPathBase<FileInfo> {

    private static final long serialVersionUID = -2115888805L;

    public static final QFileInfo fileInfo = new QFileInfo("fileInfo");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath contentType = createString("contentType");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final BooleanPath deleteFlag = createBoolean("deleteFlag");

    public final ListPath<me.weekbelt.naverreservation.domain.displayInfo.DisplayInfoImage, me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfoImage> displayInfoImages = this.<me.weekbelt.naverreservation.domain.displayInfo.DisplayInfoImage, me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfoImage>createList("displayInfoImages", me.weekbelt.naverreservation.domain.displayInfo.DisplayInfoImage.class, me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfoImage.class, PathInits.DIRECT2);

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final ListPath<me.weekbelt.naverreservation.domain.product.ProductImage, me.weekbelt.naverreservation.domain.product.QProductImage> productImages = this.<me.weekbelt.naverreservation.domain.product.ProductImage, me.weekbelt.naverreservation.domain.product.QProductImage>createList("productImages", me.weekbelt.naverreservation.domain.product.ProductImage.class, me.weekbelt.naverreservation.domain.product.QProductImage.class, PathInits.DIRECT2);

    public final ListPath<me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationUserCommentImage, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationUserCommentImage> reservationUserCommentImages = this.<me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationUserCommentImage, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationUserCommentImage>createList("reservationUserCommentImages", me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationUserCommentImage.class, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationUserCommentImage.class, PathInits.DIRECT2);

    public final StringPath saveFileName = createString("saveFileName");

    public QFileInfo(String variable) {
        super(FileInfo.class, forVariable(variable));
    }

    public QFileInfo(Path<? extends FileInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileInfo(PathMetadata metadata) {
        super(FileInfo.class, metadata);
    }

}

