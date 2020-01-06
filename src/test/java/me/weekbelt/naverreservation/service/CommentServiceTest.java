package me.weekbelt.naverreservation.service;

import me.weekbelt.naverreservation.web.dto.comment.CommentResponse;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationParam;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationPriceDto;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CommentServiceTest {

    @Autowired CommentService commentService;
    @Autowired ReservationService reservationService;
    @Autowired
    EntityManager em;

    @DisplayName("이미지없는 한줄평")
    @Test
    public void saveComment() throws Exception {
        //given

        // 상품 예약
        List<ReservationPriceDto> reservationPriceDtoList = new ArrayList<>();
        ReservationPriceDto reservationPriceDto1 = ReservationPriceDto.builder()
                .productPriceId(3L)
                .count(1)
                .build();

        ReservationPriceDto reservationPriceDto2 = ReservationPriceDto.builder()
                .productPriceId(2L)
                .count(1)
                .build();

        ReservationPriceDto reservationPriceDto3 = ReservationPriceDto.builder()
                .productPriceId(1L)
                .count(1)
                .build();

        reservationPriceDtoList.add(reservationPriceDto1);
        reservationPriceDtoList.add(reservationPriceDto2);
        reservationPriceDtoList.add(reservationPriceDto3);

        ReservationParam reservationParam = ReservationParam.builder()
                .displayInfoId(1L)
                .productId(1L)
                .reservationName("김주혁")
                .reservationTelephone("010-2727-2074")
                .reservationEmail("vfrvfr4207@hanmail.net")
                .reservationYearMonthDay("2020-01-03T18:07:44.048")
                .prices(reservationPriceDtoList)
                .build();

        Long reservationInfoId = reservationService.reservation(reservationParam);

        // 상품평 저장
        String comment = "정말 최고 였어요";
        Long productId = 1L;

        int score = 3;

        Long commentId = commentService.saveComment(null, comment, productId, reservationInfoId, score);

        em.flush();
        em.clear();

        //when
        CommentResponse commentResponse = commentService.findCommentResponse(commentId);

        //then
        assertThat(productId).isEqualTo(commentResponse.getProductId());
        assertThat(reservationInfoId).isEqualTo(commentResponse.getReservationInfoId());
        assertThat(3.0).isEqualTo(commentResponse.getScore());
        assertThat(comment).isEqualTo(commentResponse.getComment());
    }

    @DisplayName("이미지있는 한줄평")
    @Test
    public void saveCommentWithImage() throws Exception {
        //given

        //when

        //then
    }
}