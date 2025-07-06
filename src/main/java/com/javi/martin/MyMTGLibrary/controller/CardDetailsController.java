package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import com.javi.martin.MyMTGLibrary.service.CardSearchService;
import com.javi.martin.MyMTGLibrary.service.CardUIPreparer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.CURRENT_VERSION;

@Controller
@RequestMapping("/details")
public class CardDetailsController {

    @Autowired
    private CardSearchService cardSearchService;
    @Autowired
    private CardDBSearchService cardDBSearchService;
    @Autowired
    private CardUIPreparer preparer;

    @RequestMapping("/name/{cardName}")
    public String getCardDetailsByName(Model model, @PathVariable String cardName) {
        model.addAttribute("version", "Current version: " + CURRENT_VERSION);

        var apiCard = cardSearchService.searchCardByName(cardName);
        var dbCard = cardDBSearchService.getCardByName(cardName);
        model.addAttribute("cardOnDb", Objects.nonNull(dbCard));
        apiCard = preparer.prepareCard(apiCard);
        if (Objects.nonNull(dbCard)) {
            model.addAttribute("dbCard", dbCard);
        } else {
            dbCard = cardSearchService.searchCardByName(cardName);
            model.addAttribute("dbCard", dbCard);
        }
        model.addAttribute("currentCard", apiCard);

        return "details/cardDetails";
    }
}
