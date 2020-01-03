package me.weekbelt.naverreservation.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReviewViewController {

    @GetMapping("/reviewComment/{displayInfoId}")
    public String reviewComment(@PathVariable Integer displayInfoId, Model model){
        model.addAttribute("displayInfoId", displayInfoId);
        return "review";
    }
}
