package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepositoryImpl;
import me.weekbelt.naverreservation.domain.product.ProductImage;
import me.weekbelt.naverreservation.domain.product.ProductImageRepositoryImpl;
import me.weekbelt.naverreservation.web.dto.product.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final static Integer LIMIT = 4;

    private final DisplayInfoRepositoryImpl displayInfoRepository;
    private final ProductImageRepositoryImpl productImageRepository;

    public List<ProductDto> findDisplayInfoWithProduct(Long categoryId, Integer offset) {
        List<DisplayInfo> displayInfoList;

        if (categoryId == null || categoryId == 0) {
            displayInfoList = displayInfoRepository.findDisplayInfoWithProduct(offset, LIMIT);
        } else {
            displayInfoList = displayInfoRepository.findDisplayInfoWithProductByCategoryId(categoryId, offset, LIMIT);
        }

        return createProductDtos(displayInfoList);
    }

    private List<ProductDto> createProductDtos(List<DisplayInfo> displayInfoList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (DisplayInfo displayInfo : displayInfoList) {
            String saveFileName = getSaveFileNameByProductId(displayInfo.getProduct().getId());

            ProductDto productDto = new ProductDto(displayInfo, saveFileName);
            productDtoList.add(productDto);
        }

        return productDtoList;
    }

    private String getSaveFileNameByProductId(Long productId) {
        ProductImage productImage = productImageRepository.getProductImageByProductId(productId, ImageType.th).get(0);
        return productImage.getFileInfo().getSaveFileName();
    }

    public Integer countProductNumByCategoryId(Long categoryId){
        if (categoryId == null || categoryId == 0) {
            return displayInfoRepository.countDisplayInfoNumber();
        }
        return displayInfoRepository.countDisplayInfoNumberByCategoryId(categoryId);
    }
}
