package me.weekbelt.naverreservation.domain.display;

import me.weekbelt.naverreservation.web.dto.product.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class DisplayInfoRepositoryTest {
    @Autowired
    DisplayInfoRepositoryImpl displayInfoRepository;
    
    @Test
    public void findDisplayInfoWithProduct() throws Exception {
        //given
        List<DisplayInfo> displayInfoList = displayInfoRepository.findDisplayInfoWithProduct(1, 4);

        //when
        List<ProductDto> productDtoList = new ArrayList<>();
        for (DisplayInfo displayInfo : displayInfoList) {
            ProductDto productDto = new ProductDto(displayInfo, null);
            productDtoList.add(productDto);
        }

        //then
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto);
        }
    }
    
    @Test
    public void findDisplayInfoWithProductByCategoryId() throws Exception {
        //given
        List<DisplayInfo> displayInfoList = displayInfoRepository.findDisplayInfoWithProductByCategoryId(2L,1, 4);

        //when
        List<ProductDto> productDtoList = new ArrayList<>();
        for (DisplayInfo displayInfo : displayInfoList) {
            ProductDto productDto = new ProductDto(displayInfo, null);
            productDtoList.add(productDto);
        }

        //then
        for (ProductDto productDto : productDtoList) {
            System.out.println(productDto);
        }
    }
}