package me.weekbelt.naverreservation.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservation")
public class ReservationViewController {

    @GetMapping("/bookinglogin")
    public String showBookingLogin() {
        return "bookinglogin";
    }

    @GetMapping("/booking/list")
    public String bookingList(@RequestParam String reservationEmail,
                              HttpSession httpSession){

        httpSession.setAttribute("reservationEmail", reservationEmail);

        return "myreservation";
    }
}
