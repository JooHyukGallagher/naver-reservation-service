package me.weekbelt.naverreservation.web.controller;

import me.weekbelt.naverreservation.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PromotionApiControllerTest extends BaseControllerTest {

    @DisplayName("프로모션 조회 테스트")
    @Test
    public void getPromotionResponse() throws Exception {
        // when
        mockMvc.perform(get("/api/promotions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("items").exists())
        ;
    }
}