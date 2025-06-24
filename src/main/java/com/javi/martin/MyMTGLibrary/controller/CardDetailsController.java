package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.service.CardSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/details")
public class CardDetailsController {

    @Autowired
    private CardSearchService cardSearchService;

    @RequestMapping("/name/{cardName}")
    public String getCardDetailsByName(Model model, @PathVariable String cardName) {
        var card = cardSearchService.searchCardByName(cardName);
        model.addAttribute("currentCard", card);
        return "cardDetails";
    }
}
