package com.pzy.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoIndexController {

    @GetMapping("/goHome")
    public String getIndex(){
        return "redirect:/";
    }
}
