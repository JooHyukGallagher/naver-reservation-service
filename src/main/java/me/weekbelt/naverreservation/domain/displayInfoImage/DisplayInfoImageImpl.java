package me.weekbelt.naverreservation.domain.displayInfoImage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.fileInfo.QFileInfo;

import javax.persistence.EntityManager;
import java.util.Optional;

import static me.weekbelt.naverreservation.domain.displayInfo.QDisplayInfo.displayInfo;
import static me.weekbelt.naverreservation.domain.displayInfoImage.QDisplayInfoImage.displayInfoImage;
import static me.weekbelt.naverreservation.domain.fileInfo.QFileInfo.*;

@RequiredArgsConstructor
public class DisplayInfoImageImpl implements DisplayInfoImageCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<DisplayInfoImage> findDisplayInfoImageByDisplayInfoId(Long displayInfoId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(displayInfoImage)
                .join(displayInfoImage.fileInfo, fileInfo).fetchJoin()
                .join(displayInfoImage.displayInfo, displayInfo)
                .where(displayInfoImage.displayInfo.id.eq(displayInfoId))
                .fetchOne()
        );
    }
}
