package me.weekbelt.naverreservation.web.dto.reservation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReservationInfoResponse {
    private List<ReservationInfoDto> reservations;
    private Integer size;

    @Builder
    public ReservationInfoResponse(List<ReservationInfoDto> reservations, Integer size) {
        this.reservations = reservations;
        this.size = size;
    }
}
