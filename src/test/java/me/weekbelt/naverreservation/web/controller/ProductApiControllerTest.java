package me.weekbelt.naverreservation.web.controller;

import me.weekbelt.naverreservation.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductApiControllerTest extends BaseControllerTest {

    @DisplayName("정상적으로 상품 목록을 조회하는 테스트")
    @ParameterizedTest(name = "{index} {displayName}")
    @CsvSource({
            "1, 0", "1, 4", "2, 5", "3, 2", "4, 3", "5, 7"
    })
    public void getProductList(Integer categoryId, Integer start) throws Exception {
        //when
        mockMvc.perform(get("/api/products")
                .param("categoryId", categoryId.toString())
                .param("start", start.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalCount").exists())
                .andExpect(jsonPath("items").exists())
        ;
    }
}