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

    public DisplayInfoDto getDisplayInfoDto(Long displayInfoId) {
        DisplayInfo displayInfo = displayInfoRepository.findDisplayInfoWithProductAndCategoryByDisplayInfoId(displayInfoId);
        return new DisplayInfoDto(displayInfo);
    }

    public DisplayInfoImageDto findDisplayInfoImageDtoByDisplayInfoId(Long displayInfoId) {
        DisplayInfoImage displayInfoImage = displayInfoImageRepository.findDisplayInfoImageByDisplayInfoId(displayInfoId);

        return new DisplayInfoImageDto(displayInfoImage);
    }
}
