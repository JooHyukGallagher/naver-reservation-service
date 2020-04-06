package me.weekbelt.naverreservation.domain.displayInfo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDisplayInfoImage is a Querydsl query type for DisplayInfoImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDisplayInfoImage extends EntityPathBase<DisplayInfoImage> {

    private static final long serialVersionUID = 1277903208L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDisplayInfoImage displayInfoImage = new QDisplayInfoImage("displayInfoImage");

    public final QDisplayInfo displayInfo;

    public final me.weekbelt.naverreservation.domain.QFileInfo fileInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDisplayInfoImage(String variable) {
        this(DisplayInfoImage.class, forVariable(variable), INITS);
    }

    public QDisplayInfoImage(Path<? extends DisplayInfoImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDisplayInfoImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDisplayInfoImage(PathMetadata metadata, PathInits inits) {
        this(DisplayInfoImage.class, metadata, inits);
    }

    public QDisplayInfoImage(Class<? extends DisplayInfoImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.displayInfo = inits.isInitialized("displayInfo") ? new QDisplayInfo(forProperty("displayInfo"), inits.get("displayInfo")) : null;
        this.fileInfo = inits.isInitialized("fileInfo") ? new me.weekbelt.naverreservation.domain.QFileInfo(forProperty("fileInfo")) : null;
    }

}

