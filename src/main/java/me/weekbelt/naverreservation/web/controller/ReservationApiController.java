package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.service.CommentService;
import me.weekbelt.naverreservation.service.ReservationService;
import me.weekbelt.naverreservation.web.dto.comment.CommentResponse;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationInfoDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationInfoResponse;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationParam;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReservationApiController {

    private final ReservationService reservationService;
    private final CommentService commentService;

    @GetMapping("/reservations")
    public ReservationInfoResponse reservationInfo(@RequestParam String reservationEmail) {
        List<ReservationInfoDto> reservations = reservationService.findReservationInfoDto(reservationEmail);

        return ReservationInfoResponse.builder()
                .reservations(reservations)
                .size(reservations.size())
                .build();
    }

    @PostMapping("/reservations")
    public ReservationResponse createReservation(@RequestBody ReservationParam reservationParam) {
        Long reservationInfoId = reservationService.reservation(reservationParam);
        return reservationService.findReservationResponse(reservationInfoId);
    }

    @PutMapping("/reservations/{reservationInfoId}")
    public ReservationResponse cancelReservation(@PathVariable Long reservationInfoId) {
        reservationService.cancelReservation(reservationInfoId);
        return reservationService.findReservationResponse(reservationInfoId);
    }

    @GetMapping("/reservationInfo")
    public ReservationInfoDto reservationInfoByReservationInfoId(Long reservationInfoId) {
        return reservationService.findReservationInfoDto(reservationInfoId);
    }

    @PostMapping("/reservations/{reservationInfoId}/comments")
    public CommentResponse createComment(@PathVariable Long reservationInfoId,
                                         @RequestParam(required = false) MultipartFile commentImage,
                                         @RequestParam String comment,
                                         @RequestParam Long productId,
                                         @RequestParam Integer score) {
        Long commentId = commentService.saveComment(commentImage, comment, productId, reservationInfoId, score);
        return commentService.findCommentResponse(commentId);
    }

    @GetMapping("/timenow")
    public String getTime() {
        int randomValue = (int) (Math.random() * 5);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime addedLocalDateTime = localDateTime.plusDays(randomValue);

        return addedLocalDateTime.toString();
    }
}
