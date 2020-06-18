package me.weekbelt.naverreservation.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoResponse;
import me.weekbelt.naverreservation.web.dto.product.ProductDto;
import me.weekbelt.naverreservation.web.dto.product.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("전시 상품 목록을 4개씩 두번째 페이지 조회")
    @Test
    void getProducts() throws Exception {
        String requestUri = "/api/products";
        MvcResult mvcResult = mockMvc.perform(get(requestUri)
                .param("categoryId", "1")
                .param("start", "4"))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString(Charset.defaultCharset());

        ProductResponse productResponse = objectMapper.readValue(content, ProductResponse.class);

        assertThat(productResponse.getTotalCount()).isEqualTo(10);
        List<ProductDto> items = productResponse.getItems();
        assertThat(items.size()).isEqualTo(4);
        assertThat(items.get(0).getDisplayInfoId()).isEqualTo(5);
        assertThat(items.get(0).getProductId()).isEqualTo(5);
        assertThat(items.get(1).getDisplayInfoId()).isEqualTo(6);
        assertThat(items.get(1).getProductId()).isEqualTo(6);
        assertThat(items.get(2).getDisplayInfoId()).isEqualTo(7);
        assertThat(items.get(2).getProductId()).isEqualTo(7);
        assertThat(items.get(3).getDisplayInfoId()).isEqualTo(8);
        assertThat(items.get(3).getProductId()).isEqualTo(8);
    }

    @DisplayName("상품 상세 정보 조회")
    @Test
    void getProduct() throws Exception {
        String requestUri = "/api/products/{displayInfoId}";
        Long displayInfoId = 1L;

        MvcResult mvcResult = mockMvc.perform(get(requestUri, displayInfoId))
                .andDo(print())
                .andExpect(jsonPath("displayInfo").exists())
                .andExpect(jsonPath("productImages").exists())
                .andExpect(jsonPath("displayInfoImage").exists())
                .andExpect(jsonPath("comments").exists())
                .andExpect(jsonPath("averageScore").exists())
                .andExpect(jsonPath("productPrices").exists())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString(Charset.defaultCharset());

        DisplayInfoResponse displayInfoResponse = objectMapper.readValue(content, DisplayInfoResponse.class);
        assertThat(displayInfoResponse.getDisplayInfo().getDisplayInfoId())
                .isEqualTo(displayInfoId);
    }
}