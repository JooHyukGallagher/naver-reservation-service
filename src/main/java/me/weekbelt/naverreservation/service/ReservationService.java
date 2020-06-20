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
import me.weekbelt.naverreservation.util.factory.ReservationFactory;
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
        List<ReservationInfo> reservationInfos = reservationInfoRepository
                .findReservationInfoByReservationEmail(reservationEmail);

        return reservationInfos.stream()
                .map(reservationInfo -> getReservationInfoDto(reservationInfo))
                .collect(Collectors.toList());
    }

    private ReservationInfoDto getReservationInfoDto(ReservationInfo reservationInfo) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationInfo);
        List<ReservationInfoPrice> reservationInfoPriceList = reservationInfoPriceRepository
                .findReservationInfoPriceByReservationInfoId(reservationInfo.getId());
        reservationInfoDto.setTotalPrice(reservationInfo.createTotalPrice(reservationInfoPriceList));
        return reservationInfoDto;
    }

    public ReservationInfoDto findReservationInfoDto(Long reservationInfoId) {
        ReservationInfo reservationInfo = reservationInfoRepository
                .findReservationInfoByReservationInfoId(reservationInfoId);
        Integer totalPrice = reservationInfo.createTotalPrice(reservationInfoPriceRepository
                .findReservationInfoPriceByReservationInfoId(reservationInfoId));
        
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationInfo);
        reservationInfoDto.setTotalPrice(totalPrice);
        return reservationInfoDto;
    }

    public ReservationResponse findReservationResponse(Long reservationInfoId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationInfoId)
                .orElseThrow(() -> new IllegalArgumentException("예약정보가 없습니다. reservationInfoId=" + reservationInfoId));

        List<ReservationInfoPrice> reservationInfoPrices = reservationInfoPriceRepository
                .findReservationInfoPriceByReservationInfoId(reservationInfoId);
        List<ReservationPriceDto> reservationPriceDtos = reservationInfoPrices.stream()
                .map((reservationInfoPrice) -> ReservationFactory.getReservationPriceDto(reservationInfoPrice))
                .collect(Collectors.toList());

        return ReservationFactory.getReservationResponse(reservationInfo, reservationPriceDtos);
    }

    @Transactional
    public Long reservation(ReservationParam reservationParam) {
        ReservationInfo savedReservationInfo = reservationInfoRepository
                .save(makeReservationInfo(reservationParam));

        List<ReservationInfoPrice> reservationInfoPrices =
                makeReservationInfoPrices(reservationParam, savedReservationInfo);
        reservationInfoPriceRepository.saveAll(reservationInfoPrices);

        return savedReservationInfo.getId();
    }

    private ReservationInfo makeReservationInfo(ReservationParam reservationParam) {
        Product product = productRepository.findById(reservationParam.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productId=" + reservationParam.getProductId()));
        DisplayInfo displayInfo = displayInfoRepository.findById(reservationParam.getDisplayInfoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 전시정보가 없습니다. displayInfoId=" + reservationParam.getDisplayInfoId()));

        return ReservationInfo.createReservationInfo(reservationParam, product, displayInfo);
    }

    private List<ReservationInfoPrice> makeReservationInfoPrices(ReservationParam reservationParam,
                                                                 ReservationInfo reservationInfo) {
        return reservationParam.getPrices().stream()
                .map((reservationPriceDto -> getReservationInfoPrice(reservationInfo, reservationPriceDto)))
                .collect(Collectors.toList());
    }

    private ReservationInfoPrice getReservationInfoPrice(ReservationInfo reservationInfo, ReservationPriceDto reservationPriceDto) {
        ProductPrice productPrice = productPriceRepository
                .findById(reservationPriceDto.getProductPriceId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품가격이 없습니다. productPriceId=" + reservationPriceDto.getProductPriceId()));

        return ReservationFactory
                .getReservationInfoPrice(reservationInfo, reservationPriceDto, productPrice);
    }

    @Transactional
    public void cancelReservation(Long reservationInfoId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationInfoId)
                .orElseThrow(() -> new IllegalArgumentException("예약정보가 없습니다. reservationInfoId=" + reservationInfoId));
        reservationInfo.cancelReservation();
    }
}
