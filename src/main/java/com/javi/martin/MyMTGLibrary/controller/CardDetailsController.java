package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import com.javi.martin.MyMTGLibrary.service.CardSearchService;
import com.javi.martin.MyMTGLibrary.service.CardUIPreparer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        var card = cardSearchService.searchCardByName(cardName);
        card = preparer.prepareCard(card);
        model.addAttribute("currentCard", card);
        model.addAttribute("version", "Current version: " + CURRENT_VERSION);

        var isOnDb = cardDBSearchService.isCardOnDb(cardName);
        model.addAttribute("cardOnDb", isOnDb);

        return "details/cardDetails";
    }
}
