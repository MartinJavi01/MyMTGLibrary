package com.javi.martin.MyMTGLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.CURRENT_VERSION;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String toHome(Model model) {
        model.addAttribute("version", "Current version: " + CURRENT_VERSION);
        return "home/home";
    }
}
