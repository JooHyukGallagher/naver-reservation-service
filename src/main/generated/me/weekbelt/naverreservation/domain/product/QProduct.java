package me.weekbelt.naverreservation.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 2104270015L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final me.weekbelt.naverreservation.domain.QBaseEntity _super = new me.weekbelt.naverreservation.domain.QBaseEntity(this);

    public final me.weekbelt.naverreservation.domain.category.QCategory category;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath description = createString("description");

    public final ListPath<me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo, me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo> displayInfos = this.<me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo, me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo>createList("displayInfos", me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo.class, me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo.class, PathInits.DIRECT2);

    public final StringPath event = createString("event");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate = _super.modifyDate;

    public final ListPath<ProductImage, QProductImage> productImages = this.<ProductImage, QProductImage>createList("productImages", ProductImage.class, QProductImage.class, PathInits.DIRECT2);

    public final ListPath<ProductPrice, QProductPrice> productPrices = this.<ProductPrice, QProductPrice>createList("productPrices", ProductPrice.class, QProductPrice.class, PathInits.DIRECT2);

    public final ListPath<Promotion, QPromotion> promotions = this.<Promotion, QPromotion>createList("promotions", Promotion.class, QPromotion.class, PathInits.DIRECT2);

    public final ListPath<me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationInfo, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo> reservationInfos = this.<me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationInfo, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo>createList("reservationInfos", me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationInfo.class, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationInfo.class, PathInits.DIRECT2);

    public final ListPath<me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationUserComment, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationUserComment> reservationUserComments = this.<me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationUserComment, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationUserComment>createList("reservationUserComments", me.weekbelt.naverreservation.domain.reservationInfoPrice.ReservationUserComment.class, me.weekbelt.naverreservation.domain.reservationInfoPrice.QReservationUserComment.class, PathInits.DIRECT2);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new me.weekbelt.naverreservation.domain.category.QCategory(forProperty("category")) : null;
    }

}

