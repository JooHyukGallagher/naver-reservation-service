package me.weekbelt.naverreservation.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPromotion is a Querydsl query type for Promotion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPromotion extends EntityPathBase<Promotion> {

    private static final long serialVersionUID = -473491341L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPromotion promotion = new QPromotion("promotion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public QPromotion(String variable) {
        this(Promotion.class, forVariable(variable), INITS);
    }

    public QPromotion(Path<? extends Promotion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPromotion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPromotion(PathMetadata metadata, PathInits inits) {
        this(Promotion.class, metadata, inits);
    }

    public QPromotion(Class<? extends Promotion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

