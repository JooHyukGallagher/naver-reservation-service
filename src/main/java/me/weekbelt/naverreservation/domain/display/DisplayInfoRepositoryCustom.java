package me.weekbelt.naverreservation.domain.display;

import java.util.List;

public interface DisplayInfoRepositoryCustom {
    List<DisplayInfo> findDisplayInfoWithProductByCategoryId(Long categoryId, Integer start, Integer limit);

    Integer countDisplayInfoNumberByCategoryId(Long categoryId);
}
