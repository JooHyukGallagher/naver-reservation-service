package me.weekbelt.naverreservation.web.controller.view;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.reservation.ReservationInfoRepository;
import me.weekbelt.naverreservation.service.ReservationService;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationInfoDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reservation")
public class ReviewViewController {

    private final ReservationService reservationService;

    @GetMapping("/reviewComment/{displayInfoId}")
    public String reviewComment(@PathVariable Integer displayInfoId, Model model){
        model.addAttribute("displayInfoId", displayInfoId);
        return "review";
    }

    @GetMapping("/reviewWriteForm")
    public String reviewWriteForm(@RequestParam Long reservationInfoId, Model model) {
        ReservationInfoDto reservationInfoDto = reservationService.findReservationInfoDto(reservationInfoId);

        model.addAttribute("reservationInfo", reservationInfoDto);
        return "reviewWrite";
    }
}
