package me.weekbelt.naverreservation.domain.displayInfoImage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Optional;

import static me.weekbelt.naverreservation.domain.QFileInfo.fileInfo;
import static me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo.displayInfo;
import static me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfoImage.displayInfoImage;

@RequiredArgsConstructor
public class DisplayInfoImageImpl implements DisplayInfoImageCustom{

    private final EntityManager em;

    @Override
    public Optional<DisplayInfoImage> findDisplayInfoImageByDisplayInfoId(Long displayInfoId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return Optional.ofNullable(queryFactory
                .selectFrom(displayInfoImage)
                .join(displayInfoImage.fileInfo, fileInfo).fetchJoin()
                .join(displayInfoImage.displayInfo, displayInfo)
                .where(displayInfoImage.displayInfo.id.eq(displayInfoId))
                .fetchOne()
        );
    }
}
