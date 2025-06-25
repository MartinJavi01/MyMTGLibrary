package com.javi.martin.MyMTGLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String toHome(Model model) {
        model.addAttribute("message", "hola wenas tardes");
        return "home/home";
    }
}
