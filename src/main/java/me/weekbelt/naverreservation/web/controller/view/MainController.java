package me.weekbelt.naverreservation.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/reservation/mainpage"})
    public String showMainPage() {
        return "mainpage";
    }

    @GetMapping("/reservation/detail/{displayInfoId}")
    public String showDisplayInfo(@PathVariable Long displayInfoId, Model model) {
        model.addAttribute("displayInfoId", displayInfoId);

        return "detail";
    }
}
