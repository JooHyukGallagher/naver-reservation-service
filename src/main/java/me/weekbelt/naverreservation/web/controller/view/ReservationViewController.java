package me.weekbelt.naverreservation.web.controller.view;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reservation")
public class ReservationViewController {

    @GetMapping("/bookinglogin")
    public String showBookingLogin() {
        return "bookinglogin";
    }

    @GetMapping("/reservationForm/{displayInfoId}")
    public String showReservationForm(@PathVariable Long displayInfoId, Model model, HttpSession httpSession) {

        DisplayInfoResponse displayInfoResponse = (DisplayInfoResponse) httpSession.getAttribute("displayInfoResponse");

        model.addAttribute("displayInfoId", displayInfoId);
        model.addAttribute("displayInfoResponse", displayInfoResponse);

        httpSession.removeAttribute("displayInfoResponse");

        return "reserve";
    }

    @GetMapping("/booking/list")
    public String bookingList(@RequestParam String reservationEmail, HttpSession httpSession) {

        httpSession.setAttribute("reservationEmail", reservationEmail);

        return "myreservation";
    }

}
