package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import com.javi.martin.MyMTGLibrary.service.CardFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.CURRENT_VERSION;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CardDBSearchService cardDBSearchService;
    @Autowired
    private CardFilterService filterService;

    @GetMapping
    public String showAllCollection(Model model,
                                    @RequestParam(required = false, value = "type") String typeFilter,
                                    @RequestParam(required = false, value = "subType") String subTypeFilter) {
        model.addAttribute("version", "Current version: " + CURRENT_VERSION);

        var cards = cardDBSearchService.getAllCards();
        if (Objects.nonNull(typeFilter)) {
            cards = filterService.filterCardsByType(cards, typeFilter);
        }
        if (Objects.nonNull(subTypeFilter)) {
            cards = filterService.filterCardsByCardSubType(cards, subTypeFilter);
        }
        model.addAttribute("cards", cards);

        if(Objects.nonNull(typeFilter) || Objects.nonNull(subTypeFilter)) {
            model.addAttribute("filtering", true);
        } else {
            model.addAttribute("filtering", false);
        }

        return "collection/collection";
    }
}
