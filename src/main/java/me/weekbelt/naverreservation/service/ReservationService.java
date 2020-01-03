package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepository;
import me.weekbelt.naverreservation.domain.product.Product;
import me.weekbelt.naverreservation.domain.product.ProductPrice;
import me.weekbelt.naverreservation.domain.product.ProductPriceRepository;
import me.weekbelt.naverreservation.domain.product.ProductRepository;
import me.weekbelt.naverreservation.domain.reservation.*;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
import me.weekbelt.naverreservation.web.dto.product.ProductPriceDto;
import me.weekbelt.naverreservation.web.dto.product.ProductResponse;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationInfoDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationParam;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationPriceDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationUserCommentRepository reservationUserCommentRepository;
    private final ReservationInfoRepository reservationInfoRepository;
    private final ReservationInfoPriceRepository reservationInfoPriceRepository;
    private final ProductPriceRepository productPriceRepository;
    private final DisplayInfoRepository displayInfoRepository;
    private final ProductRepository productRepository;

    public List<CommentDto> findCommentDto(Long productId) {
        List<ReservationUserComment> reservationUserComments = reservationUserCommentRepository.findReservationUserCommentByProductId(productId);

        return reservationUserComments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

    public Double findAverageScore(Long productId) {
        double averageScore = 0.0;

        List<ReservationUserComment> reservationUserComments = reservationUserCommentRepository.findReservationUserCommentByProductId(productId);
        if (!reservationUserComments.isEmpty()){
            for (ReservationUserComment reservationUserComment : reservationUserComments) {
                averageScore += reservationUserComment.getScore();
            }
            averageScore /= reservationUserComments.size();
        }

        return averageScore;
    }

    public List<ReservationInfoDto> findReservationInfoDto(String reservationEmail) {
        List<ReservationInfo> reservationInfos = reservationInfoRepository.findReservationInfoByReservationEmail(reservationEmail);

        return reservationInfos.stream()
                .map(reservationInfo -> {
                    ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationInfo);
                    reservationInfoDto.setTotalPrice(createTotalPrice(reservationInfo.getId()));

                    return reservationInfoDto;
                })
                .collect(Collectors.toList());
    }

    private Integer createTotalPrice(Long reservationInfoId) {
        List<ReservationInfoPrice> reservationInfoPriceList = reservationInfoPriceRepository.findReservationInfoPriceByReservationInfoId(reservationInfoId);
        int sum = 0;

        for (ReservationInfoPrice reservationInfoPrice : reservationInfoPriceList) {
            int count = reservationInfoPrice.getCount();
            int price = reservationInfoPrice.getProductPrice().getPrice();
            sum += count * price;
        }

        return sum;
    }

    public ReservationResponse saveReservation(ReservationParam reservationParam) {
        ReservationInfo reservationInfo = createReservationInfo(reservationParam);

        List<ReservationPriceDto> reservationPriceDtoList = reservationParam.getPrices();
        List<ReservationInfoPrice> reservationInfoPrices = reservationPriceDtoList.stream()
                .map(reservationPriceDto -> {
                    ProductPrice productPrice = productPriceRepository.findById(reservationPriceDto.getProductPriceId())
                            .orElseThrow(() -> new IllegalArgumentException("존재하는 상품 가격이 없습니다. id=" + reservationPriceDto.getProductPriceId()));

                    return ReservationInfoPrice.builder()
                            .reservationInfo(reservationInfo)
                            .productPrice(productPrice)
                            .count(reservationPriceDto.getCount())
                            .build();
                })
                .collect(Collectors.toList());

        reservationInfoPriceRepository.saveAll(reservationInfoPrices);
        Long reservationInfoId = reservationInfoRepository.save(reservationInfo).getId();

        return createReservationResponse(reservationInfoId);
    }

    private ReservationInfo createReservationInfo(ReservationParam reservationParam) {
        Product product = productRepository.findById(reservationParam.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다. id=" + reservationParam.getProductId()));
        DisplayInfo displayInfo = displayInfoRepository.findById(reservationParam.getDisplayInfoId())
                .orElseThrow(() -> new IllegalArgumentException("전시정보가 없습니다. id=" + reservationParam.getDisplayInfoId()));

        return ReservationInfo.builder()
                .product(product)
                .displayInfo(displayInfo)
                .reservationName(reservationParam.getReservationName())
                .reservationTel(reservationParam.getReservationTelephone())
                .reservationEmail(reservationParam.getReservationEmail())
                .reservationDate(convertStringToLocalDateTime(reservationParam.getReservationYearMonthDay()))
                .build();
    }

    private LocalDateTime convertStringToLocalDateTime(String reservationDate){
        return LocalDateTime.parse(reservationDate, DateTimeFormatter.ISO_DATE);
    }

    private String convertLocalDateTimeToString(LocalDateTime reservationDate){
        return reservationDate.toString();
    }

    private ReservationResponse createReservationResponse(Long reservationInfoId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationInfoId)
                .orElseThrow(() -> new IllegalArgumentException("예약 정보가 없습니다. id=" + reservationInfoId));

        List<ReservationInfoPrice> reservationInfoPriceList = reservationInfoPriceRepository.findReservationInfoPriceByReservationInfoId(reservationInfoId);
        List<ReservationPriceDto> prices = reservationInfoPriceList.stream()
                .map((reservationInfoPrice) -> ReservationPriceDto.builder()
                            .reservationInfoPriceId(reservationInfoPrice.getId())
                            .reservationInfoId(reservationInfoPrice.getReservationInfo().getId())
                            .productPriceId(reservationInfoPrice.getProductPrice().getId())
                            .count(reservationInfoPrice.getCount())
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
                .reservationDate(convertLocalDateTimeToString(reservationInfo.getReservationDate()))
                .createDate(reservationInfo.getCreateDate())
                .modifyDate(reservationInfo.getModifyDate())
                .prices(prices)
                .build();
    }

}
