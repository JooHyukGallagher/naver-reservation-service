package me.weekbelt.naverreservation.domain.product;

import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepositoryImpl;
import me.weekbelt.naverreservation.web.dto.product.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class ProductImageRepositoryTest {

    @Autowired
    DisplayInfoRepositoryImpl displayInfoRepository;

    @Autowired
    ProductImageRepositoryImpl productImageRepository;

    @Test
    public void getProductImageByProductId() throws Exception {
        //given
        List<DisplayInfo> displayInfoWithProduct = displayInfoRepository.findDisplayInfoWithProduct(0, 4);

        //when
        List<ProductDto> productDtoList = new ArrayList<>();
        for (DisplayInfo displayInfo : displayInfoWithProduct) {
            Long productId = displayInfo.getProduct().getId();
            List<ProductImage> productImages = productImageRepository.getProductImageByProductId(productId, ImageType.th);

            ProductImage productImage = productImages.get(0);
            ProductDto productDto = new ProductDto(displayInfo, productImage.getFileInfo().getSaveFileName());
            productDtoList.add(productDto);
        }
        //then
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto);
        }
    }
}