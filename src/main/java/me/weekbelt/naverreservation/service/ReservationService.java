package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.display.DisplayInfo;
import me.weekbelt.naverreservation.domain.display.DisplayInfoRepository;
import me.weekbelt.naverreservation.domain.reservation.*;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationUserCommentRepository reservationUserCommentRepository;
    private final ReservationInfoRepository reservationInfoRepository;
    private final DisplayInfoRepository displayInfoRepository;
    private final ReservationInfoPriceRepository reservationInfoPriceRepository;

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
}
