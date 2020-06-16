package me.weekbelt.naverreservation.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.weekbelt.naverreservation.web.dto.promotion.PromotionResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PromotionApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("프로모션 상품 목록 조회")
    @Test
    void getPromotions() throws Exception {
        String requestUri = "/api/promotions";
        MvcResult mvcResult = mockMvc.perform(get(requestUri))
                .andDo(print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString(Charset.defaultCharset());
        PromotionResponse promotionResponse = objectMapper.readValue(content, PromotionResponse.class);

        Assertions.assertThat(promotionResponse.getItems().size()).isEqualTo(11);
    }
}