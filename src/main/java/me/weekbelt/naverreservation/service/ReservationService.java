package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo;
import me.weekbelt.naverreservation.domain.displayInfo.DisplayInfoRepository;
import me.weekbelt.naverreservation.domain.product.Product;
import me.weekbelt.naverreservation.domain.productPrice.ProductPrice;
import me.weekbelt.naverreservation.domain.productPrice.ProductPriceRepository;
import me.weekbelt.naverreservation.domain.product.ProductRepository;
import me.weekbelt.naverreservation.domain.reservationInfoPrice.*;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfo;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfoRepository;
import me.weekbelt.naverreservation.util.TimeUtil;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationInfoDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationParam;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationPriceDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationInfoRepository reservationInfoRepository;
    private final ReservationInfoPriceRepository reservationInfoPriceRepository;
    private final ProductPriceRepository productPriceRepository;
    private final DisplayInfoRepository displayInfoRepository;
    private final ProductRepository productRepository;

    public List<ReservationInfoDto> findReservationInfoDto(String reservationEmail) {
        List<ReservationInfo> reservationInfos = reservationInfoRepository.findReservationInfoByReservationEmail(reservationEmail);

        return reservationInfos.stream()
                .map(reservationInfo -> {
                    ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationInfo);
                    List<ReservationInfoPrice> reservationInfoPriceList = reservationInfoPriceRepository
                            .findReservationInfoPriceByReservationInfoId(reservationInfo.getId());
                    reservationInfoDto.setTotalPrice(reservationInfo.createTotalPrice(reservationInfoPriceList));
                    return reservationInfoDto;
                })
                .collect(Collectors.toList());
    }


    public ReservationInfoDto findReservationInfoDto(Long reservationInfoId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findReservationInfoByReservationInfoId(reservationInfoId);

        return new ReservationInfoDto(reservationInfo);
    }

    public ReservationResponse findReservationResponse(Long reservationInfoId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationInfoId)
                .orElseThrow(() -> new IllegalArgumentException("예약정보가 없습니다. reservationInfoId=" + reservationInfoId));

        List<ReservationInfoPrice> reservationInfoPrices = reservationInfoPriceRepository.findReservationInfoPriceByReservationInfoId(reservationInfoId);
        List<ReservationPriceDto> reservationPriceDtos = reservationInfoPrices.stream()
                .map((reservationInfoPrice) -> ReservationPriceDto.builder()
                        .reservationInfoPriceId(reservationInfoPrice.getId())
                        .reservationInfoId(reservationInfoPrice.getReservationInfo().getId())
                        .productPriceId(reservationInfoPrice.getProductPrice().getId())
                        .build())
                .collect(Collectors.toList());

        return ReservationResponse.builder()
                .reservationInfoId(reservationInfo.getId())
                .productId(reservationInfo.getProduct().getId())
                .displayInfoId(reservationInfo.getDisplayInfo().getId())
                .reservationName(reservationInfo.getReservationName())
                .reservationTelephone(reservationInfo.getReservationTel())
                .reservationEmail(reservationInfo.getReservationEmail())
                .cancelYn(reservationInfo.isCancelFlag())
                .reservationDate(TimeUtil.convertLocalDateTimeToString(reservationInfo.getReservationDate()))
                .createDate(reservationInfo.getCreateDate())
                .modifyDate(reservationInfo.getModifyDate())
                .prices(reservationPriceDtos)
                .build();
    }

    @Transactional
    public Long reservation(ReservationParam reservationParam) {
        ReservationInfo newReservationInfo = makeReservationInfo(reservationParam);
        ReservationInfo savedReservationInfo = reservationInfoRepository.save(newReservationInfo);

        List<ReservationInfoPrice> reservationInfoPrices = makeReservationInfoPrices(reservationParam, savedReservationInfo);
        reservationInfoPriceRepository.saveAll(reservationInfoPrices);

        return savedReservationInfo.getId();
    }

    private List<ReservationInfoPrice> makeReservationInfoPrices(ReservationParam reservationParam, ReservationInfo reservationInfo) {
        return reservationParam.getPrices().stream()
                .map((reservationPriceDto -> {
                    ProductPrice productPrice = productPriceRepository.findById(reservationPriceDto.getProductPriceId())
                            .orElseThrow(() -> new IllegalArgumentException("해당 상품가격이 없습니다. productPriceId=" + reservationPriceDto.getProductPriceId()));

                    return ReservationInfoPrice.builder()
                            .reservationInfo(reservationInfo)
                            .productPrice(productPrice)
                            .count(reservationPriceDto.getCount())
                            .build();
                })).collect(Collectors.toList());
    }

    private ReservationInfo makeReservationInfo(ReservationParam reservationParam) {
        Product product = productRepository.findById(reservationParam.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productId=" + reservationParam.getProductId()));
        DisplayInfo displayInfo = displayInfoRepository.findById(reservationParam.getDisplayInfoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 전시정보가 없습니다. displayInfoId=" + reservationParam.getDisplayInfoId()));

        return ReservationInfo.createReservationInfo(reservationParam, product, displayInfo);
    }

    @Transactional
    public void cancelReservation(Long reservationInfoId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationInfoId)
                .orElseThrow(() -> new IllegalArgumentException("예약정보가 없습니다. reservationInfoId=" + reservationInfoId));
        reservationInfo.cancelReservation();
    }
}
