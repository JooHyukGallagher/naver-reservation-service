package me.weekbelt.naverreservation.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo;
import me.weekbelt.naverreservation.service.CommentService;
import me.weekbelt.naverreservation.service.DisplayInfoService;
import me.weekbelt.naverreservation.service.ProductService;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoDto;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoImageDto;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoResponse;
import me.weekbelt.naverreservation.web.dto.product.ProductImageDto;
import me.weekbelt.naverreservation.web.dto.product.ProductPriceDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationParam;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationPriceDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductService productService;
    @Autowired
    DisplayInfoService displayInfoService;
    @Autowired
    CommentService commentService;

    @DisplayName("예약정보 조회")
    @Test
    void getReservations() throws Exception {
        String requestUri = "/api/reservations";
        String reservationEmail = "dorosi@connect.co.kr";
        mockMvc.perform(get(requestUri)
                .param("reservationEmail", reservationEmail))
                .andDo(print())
                .andExpect(jsonPath("reservations").exists())
        ;
    }
//
//    @DisplayName("예약 하기 - 첫번째 전시상품 예")
//    @Test
//    void createReservation() throws Exception {
//        String requestUri = "/api/reservations";
//        DisplayInfoResponse displayInfoResponse = createDisplayInfoResponse(1L);
//
//        List<ProductPriceDto> productPrices = displayInfoResponse.getProductPrices();
//
//        ReservationParam.builder()
//                .displayInfoId(displayInfoResponse.getDisplayInfo().getDisplayInfoId())
//                .productId(displayInfoResponse.getDisplayInfo().getProductId())
//                .reservationEmail("vfrvfr4207@gmail.com")
//                .reservationName("김주혁")
//                .reservationTelephone("010-2727-2074")
//                .reservationYearMonthDay(LocalDateTime.now().toString())
//                .build();
//
//
//    }
//
//    DisplayInfoResponse createDisplayInfoResponse(Long displayInfoId){
//        DisplayInfoDto displayInfo = displayInfoService.findDisplayInfoDto(displayInfoId);
//        DisplayInfoImageDto displayInfoImage = displayInfoService.findDisplayInfoImageDto(displayInfoId);
//
//        Long productId = displayInfo.getProductId();
//        List<ProductImageDto> productImages = productService.findProductImageDto(productId, ImageType.ma);
//        List<CommentDto> comments = commentService.findCommentDto(productId);
//        List<ProductPriceDto> productPrices = productService.findProductPriceDto(productId);
//        Double averageScore = commentService.findAverageScore(productId);
//
//        return DisplayInfoResponse.builder()
//                .displayInfo(displayInfo)
//                .displayInfoImage(displayInfoImage)
//                .productImages(productImages)
//                .comments(comments)
//                .productPrices(productPrices)
//                .averageScore(averageScore)
//                .build();
//    }
}