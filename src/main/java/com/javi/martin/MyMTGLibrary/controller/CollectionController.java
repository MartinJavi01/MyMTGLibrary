package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CardDBSearchService cardDBSearchService;

    @GetMapping
    public String showAllCollection(Model model) {

        model.addAttribute("cards", cardDBSearchService.getAllCards());

        return "collection/collection";
    }
}
