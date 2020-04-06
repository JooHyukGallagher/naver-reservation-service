package me.weekbelt.naverreservation.domain.displayInfoImage;

import java.util.Optional;

public interface DisplayInfoImageCustom {
    Optional<DisplayInfoImage> findDisplayInfoImageByDisplayInfoId(Long displayInfoId);
}
