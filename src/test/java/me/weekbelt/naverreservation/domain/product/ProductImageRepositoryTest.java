package me.weekbelt.naverreservation.domain.product;


import me.weekbelt.naverreservation.domain.ImageType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductImageRepositoryTest {
//
//    @Autowired
//    private ProductImageRepository productImageRepository;
//
//    @DisplayName("")
//    @Test
//    public void findProductImageByProductIdAndType() throws Exception {
//        List<ProductImage> productImageList = productImageRepository.findProductImageByProductIdAndType(1L, ImageType.th);
//
//        assertThat(productImageList.get(0)).isNotNull();
//    }
}