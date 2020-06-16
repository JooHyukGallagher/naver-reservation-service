package me.weekbelt.naverreservation.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.weekbelt.naverreservation.web.dto.category.CategoryDto;
import me.weekbelt.naverreservation.web.dto.category.CategoryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sun.nio.cs.UTF_8;

import java.nio.charset.Charset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("카테고리 목록 조회")
    @Test
    void getCategories() throws Exception {
        String requestUri = "/api/categories";
        MvcResult mvcResult = mockMvc.perform(get(requestUri))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString(Charset.defaultCharset());

        CategoryResponse categoryResponse = objectMapper.readValue(content, CategoryResponse.class);

        List<CategoryDto> items = categoryResponse.getItems();
        assertThat(items.size()).isEqualTo(5);
        assertThat(items.get(0).getCategoryId()).isEqualTo(1);
        assertThat(items.get(0).getName()).isEqualTo("전시");
        assertThat(items.get(1).getCategoryId()).isEqualTo(2);
        assertThat(items.get(1).getName()).isEqualTo("뮤지컬");
        assertThat(items.get(2).getCategoryId()).isEqualTo(3);
        assertThat(items.get(2).getName()).isEqualTo("콘서트");
        assertThat(items.get(3).getCategoryId()).isEqualTo(4);
        assertThat(items.get(3).getName()).isEqualTo("클래식");
        assertThat(items.get(4).getCategoryId()).isEqualTo(5);
        assertThat(items.get(4).getName()).isEqualTo("연극");
    }
}