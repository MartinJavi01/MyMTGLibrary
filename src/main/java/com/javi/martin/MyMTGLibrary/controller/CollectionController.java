package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.CURRENT_VERSION;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CardDBSearchService cardDBSearchService;

    @GetMapping
    public String showAllCollection(Model model) {
        model.addAttribute("version", "Current version: " + CURRENT_VERSION);
        model.addAttribute("cards", cardDBSearchService.getAllCards());

        return "collection/collection";
    }
}
