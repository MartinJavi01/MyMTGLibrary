package com.javi.martin.MyMTGLibrary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import com.javi.martin.MyMTGLibrary.service.CardSearchService;
import com.javi.martin.MyMTGLibrary.service.CardUIPreparerService;
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
    private CardUIPreparerService preparer;

    @RequestMapping("/name/{cardName}")
    public String getCardDetailsByName(Model model, @PathVariable String cardName) throws JsonProcessingException {
        model.addAttribute("version", "Current version: " + CURRENT_VERSION);

        var apiCard = cardSearchService.searchCardByName(cardName);
        var dbCard = cardDBSearchService.getCardByName(apiCard.getName());
        model.addAttribute("cardOnDb", Objects.nonNull(dbCard));
        apiCard = preparer.prepareCard(apiCard);
        if (Objects.isNull(dbCard)) {
            dbCard = cardSearchService.searchCardByName(cardName);
        }

        model.addAttribute("dbCard", cardSearchService.returnCardAsJson(dbCard));
        model.addAttribute("copies", "" + dbCard.getCopies());
        model.addAttribute("currentCard", apiCard);

        if (preparer.isDoubleCard(dbCard)) {
            model.addAttribute("currentFace", 1);
        } else {
            model.addAttribute("currentFace", -1);
        }
        return "details/cardDetails";
    }

    @RequestMapping("/id/{id}")
    public String getCardDetailsById(Model model, @PathVariable String id) throws JsonProcessingException {
        model.addAttribute("version", "Current version: " + CURRENT_VERSION);

        var apiCard = cardSearchService.searchCardById(id);
        var dbCard = cardDBSearchService.getCardById(id);
        model.addAttribute("cardOnDb", Objects.nonNull(dbCard));
        apiCard = preparer.prepareCard(apiCard);
        if (Objects.isNull(dbCard)) {
            dbCard = cardDBSearchService.getCardById(id);
        }

        model.addAttribute("dbCard", cardSearchService.returnCardAsJson(dbCard));
        model.addAttribute("copies", "" + dbCard.getCopies());
        model.addAttribute("currentCard", apiCard);

        return "details/cardDetails";
    }
}
