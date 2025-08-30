package com.javi.martin.MyMTGLibrary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import com.javi.martin.MyMTGLibrary.service.CardSearchService;
import com.javi.martin.MyMTGLibrary.service.CardUIPreparerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.CURRENT_VERSION;

@RestController
@RequestMapping("/details")
@CrossOrigin(origins = "http://localhost:4200")
public class CardDetailsController {

    @Autowired
    private CardSearchService cardSearchService;
    @Autowired
    private CardDBSearchService cardDBSearchService;
    @Autowired
    private CardUIPreparerService preparer;

    @RequestMapping("/name/{cardName}")
    public MTGCardDTO getCardDetailsByName(@PathVariable String cardName) {
        var apiCard = cardSearchService.searchCardByName(cardName);
        var dbCard = cardDBSearchService.getCardByName(apiCard.getName());
        if (Objects.nonNull(dbCard)) {
            return dbCard;
        } else {
            return apiCard;
        }
    }

    @RequestMapping("/id/{id}")
    public MTGCardDTO getCardDetailsById(@PathVariable String id) {
        var apiCard = cardSearchService.searchCardById(id);
        var dbCard = cardDBSearchService.getCardById(id);
        if (Objects.nonNull(dbCard)) {
            return dbCard;
        } else {
            return apiCard;
        }
    }

    @RequestMapping("/json")
    public String getCardJsonString(@RequestBody MTGCardDTO card) throws JsonProcessingException {
        return cardSearchService.returnCardAsJson(card);
    }
}
