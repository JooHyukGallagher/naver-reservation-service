package me.weekbelt.naverreservation.domain.displayInfo;

import java.util.List;
import java.util.Optional;

public interface DisplayInfoRepositoryCustom {
    List<DisplayInfo> findDisplayInfoWithProductByCategoryId(Long categoryId, Integer start, Integer limit);

    Integer countDisplayInfoNumberByCategoryId(Long categoryId);

    Optional<DisplayInfo> findDisplayInfoByDisplayInfoId(Long displayInfoId);
}
