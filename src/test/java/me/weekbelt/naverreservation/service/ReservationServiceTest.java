package me.weekbelt.naverreservation.service;

import me.weekbelt.naverreservation.domain.reservation.ReservationInfo;
import me.weekbelt.naverreservation.domain.reservation.ReservationInfoRepository;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationParam;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationPriceDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired ReservationService reservationService;
    @Autowired
    ReservationInfoRepository reservationInfoRepository;
    @Autowired
    EntityManager em;

    @Test
    public void saveReservationTest() throws Exception {
        //given
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

        //when
        ReservationResponse reservationResponse = reservationService.findReservationResponse(reservationInfoId);

        List<ReservationPriceDto> reservationPriceDtos = reservationResponse.getPrices();


        // 반대
        assertThat(reservationResponse.getReservationInfoId()).isEqualTo(reservationInfoId);
        assertThat(reservationParam.getProductId()).isEqualTo(reservationResponse.getProductId());
        assertThat(reservationParam.getDisplayInfoId()).isEqualTo(reservationResponse.getDisplayInfoId());

        assertThat((reservationParam.getReservationName())).isEqualTo(reservationResponse.getReservationName());
        assertThat((reservationParam.getReservationTelephone())).isEqualTo(reservationResponse.getReservationTelephone());
        assertThat((reservationParam.getReservationEmail())).isEqualTo(reservationResponse.getReservationEmail());
        assertThat(("2020-01-03")).isEqualTo(reservationResponse.getReservationDate());
        assertThat((false)).isEqualTo(reservationResponse.isCancelYn());

        assertThat(reservationPriceDtos.size()).isEqualTo(3);
    }

    @Test
    public void cancelReservationTest() throws Exception {
        //given
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

        //when
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationInfoId).get();
        reservationInfo.cancelReservation();

        em.flush();
        em.clear();

        ReservationResponse reservationResponse = reservationService.findReservationResponse(reservationInfoId);
        List<ReservationPriceDto> reservationPriceDtos = reservationResponse.getPrices();


        // 반대
        assertThat(reservationResponse.getReservationInfoId()).isEqualTo(reservationInfoId);
        assertThat(reservationParam.getProductId()).isEqualTo(reservationResponse.getProductId());
        assertThat(reservationParam.getDisplayInfoId()).isEqualTo(reservationResponse.getDisplayInfoId());

        assertThat((reservationParam.getReservationName())).isEqualTo(reservationResponse.getReservationName());
        assertThat((reservationParam.getReservationTelephone())).isEqualTo(reservationResponse.getReservationTelephone());
        assertThat((reservationParam.getReservationEmail())).isEqualTo(reservationResponse.getReservationEmail());
        assertThat(("2020-01-03")).isEqualTo(reservationResponse.getReservationDate());
        assertThat((true)).isEqualTo(reservationResponse.isCancelYn());

        assertThat(reservationPriceDtos.size()).isEqualTo(3);
    }
}
