package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.display.DisplayInfoImage;
import me.weekbelt.naverreservation.domain.display.DisplayInfoImageRepository;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepository;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoDto;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoImageDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DisplayInfoService {

    private final DisplayInfoRepository displayInfoRepository;
    private final DisplayInfoImageRepository displayInfoImageRepository;

    public DisplayInfoDto findDisplayInfoDto(Long displayInfoId) {
        DisplayInfo displayInfo = displayInfoRepository.findDisplayInfoWithProductAndCategoryByDisplayInfoId(displayInfoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 전시정보가 없습니다. id=" + displayInfoId));
        return new DisplayInfoDto(displayInfo);
    }

    public DisplayInfoImageDto findDisplayInfoImageDTo(Long displayInfoId) {
        DisplayInfoImage displayInfoImage = displayInfoImageRepository.findDisplayInfoImageByDisplayInfoId(displayInfoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다. id=" + displayInfoId));

        return new DisplayInfoImageDto(displayInfoImage);
    }
}
