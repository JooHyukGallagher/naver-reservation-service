package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.service.ReservationService;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationInfoDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationInfoResponse;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationParam;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReservationApiController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public ReservationInfoResponse reservationInfo(@RequestParam String reservationEmail){
        List<ReservationInfoDto> reservations = reservationService.findReservationInfoDto(reservationEmail);

        return ReservationInfoResponse.builder()
                .reservations(reservations)
                .size(reservations.size())
                .build();
    }

    @PostMapping("/reservations")
    public ReservationResponse createReservation(@RequestBody ReservationParam reservationParam){
        reservationService.saveReservation(reservationParam);
        return new ReservationResponse();
    }

    @GetMapping("/timenow")
    public String getTime() {
        int randomValue = (int) (Math.random() * 5);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime addedLocalDateTime = localDateTime.plusDays(randomValue);

        return addedLocalDateTime.toString();
    }
}
