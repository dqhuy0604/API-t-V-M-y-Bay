package com.mycompany;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/adminController")
    public String showHomePage() {
        return "adminController";
    }

    @GetMapping("/index")
    public String showHomePage1() {
        return "index";
    }

}
