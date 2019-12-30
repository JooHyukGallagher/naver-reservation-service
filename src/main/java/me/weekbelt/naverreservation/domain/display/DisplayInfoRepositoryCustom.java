package me.weekbelt.naverreservation.domain.display;

import java.util.List;

public interface DisplayInfoRepositoryCustom {
    List<DisplayInfo> findDisplayInfoWithProduct(Integer start, Integer limit);

    List<DisplayInfo> findDisplayInfoWithProductByCategoryId(Long categoryId, Integer start, Integer limit);

    Integer countDisplayInfoNumber();

    Integer countDisplayInfoNumberByCategoryId(Long categoryId);
}
