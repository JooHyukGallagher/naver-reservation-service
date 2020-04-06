package me.weekbelt.naverreservation.domain.displayInfo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDisplayInfo is a Querydsl query type for DisplayInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDisplayInfo extends EntityPathBase<DisplayInfo> {

    private static final long serialVersionUID = 1274630067L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDisplayInfo displayInfo = new QDisplayInfo("displayInfo");

    public final me.weekbelt.naverreservation.domain.QBaseEntity _super = new me.weekbelt.naverreservation.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath email = createString("email");

    public final StringPath homepage = createString("homepage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final StringPath openingHours = createString("openingHours");

    public final StringPath placeLot = createString("placeLot");

    public final StringPath placeName = createString("placeName");

    public final StringPath placeStreet = createString("placeStreet");

    public final me.weekbelt.naverreservation.domain.product.QProduct product;

    public final ListPath<me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationInfo, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo> reservationInfos = this.<me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationInfo, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo>createList("reservationInfos", me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationInfo.class, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo.class, PathInits.DIRECT2);

    public final StringPath tel = createString("tel");

    public QDisplayInfo(String variable) {
        this(DisplayInfo.class, forVariable(variable), INITS);
    }

    public QDisplayInfo(Path<? extends DisplayInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDisplayInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDisplayInfo(PathMetadata metadata, PathInits inits) {
        this(DisplayInfo.class, metadata, inits);
    }

    public QDisplayInfo(Class<? extends DisplayInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new me.weekbelt.naverreservation.domain.product.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

