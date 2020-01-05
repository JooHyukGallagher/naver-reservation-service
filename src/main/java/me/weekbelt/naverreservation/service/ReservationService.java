package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepository;
import me.weekbelt.naverreservation.domain.product.Product;
import me.weekbelt.naverreservation.domain.product.ProductPrice;
import me.weekbelt.naverreservation.domain.product.ProductPriceRepository;
import me.weekbelt.naverreservation.domain.product.ProductRepository;
import me.weekbelt.naverreservation.domain.reservation.*;
import me.weekbelt.naverreservation.util.TimeUtil;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
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

    @Transactional
    public Long reservation(ReservationParam reservationParam) {
        // 엔티티 조회
        Product product = productRepository.findById(reservationParam.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productId=" + reservationParam.getProductId()));
        DisplayInfo displayInfo = displayInfoRepository.findById(reservationParam.getDisplayInfoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 전시정보가 없습니다. displayInfoId=" + reservationParam.getDisplayInfoId()));

        // ReservationInfo 생성
        ReservationInfo reservationInfo = ReservationInfo.createReservationInfo(reservationParam, product, displayInfo);

        // ReservationInfoPrice 생성
        List<ReservationInfoPrice> reservationInfoPrices = reservationParam.getPrices().stream()
                .map((reservationPriceDto) -> {
                    ProductPrice productPrice = productPriceRepository.findById(reservationPriceDto.getProductPriceId())
                            .orElseThrow(() -> new IllegalArgumentException("해당 상품가격이 없습니다. productPriceId=" + reservationPriceDto.getProductPriceId()));

                    ReservationInfoPrice reservationInfoPrice = ReservationInfoPrice.builder()
                            .productPrice(productPrice)
                            .count(reservationPriceDto.getCount())
                            .build();

                    reservationInfoPrice.setReservationInfo(reservationInfo);

                    return reservationInfoPrice;
                })
                .collect(Collectors.toList());

        // 예약 정보 & 가격 저장
        List<ReservationInfoPrice> result =  reservationInfoPriceRepository.saveAll(reservationInfoPrices);

        return reservationInfo.getId();
    }

    @Transactional
    public void cancelReservation(Long reservationInfoId) {
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationInfoId)
                .orElseThrow(() -> new IllegalArgumentException("예약정보가 없습니다. reservationInfoId=" + reservationInfoId));
        reservationInfo.cancelReservation();
    }
}
