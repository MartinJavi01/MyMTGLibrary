package com.javi.martin.MyMTGLibrary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.service.CardDBSearchService;
import com.javi.martin.MyMTGLibrary.service.CardSearchService;
import com.javi.martin.MyMTGLibrary.service.CardUIPreparerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.CURRENT_VERSION;

@RestController
@RequestMapping("/details")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class CardDetailsController {

    @Autowired
    private CardSearchService cardSearchService;
    @Autowired
    private CardDBSearchService cardDBSearchService;
    @Autowired
    private CardUIPreparerService preparer;

    @GetMapping("/name/{cardName}")
    public String getCardDetailsByName(@PathVariable String cardName) throws JsonProcessingException {
        log.info("Received GET for /name/{}", cardName);

        var apiCard = cardSearchService.searchCardByName(cardName);
        var dbCard = cardDBSearchService.getCardByName(apiCard.getName());
        if (Objects.nonNull(dbCard)) {
            dbCard = preparer.prepareCard(dbCard);
            return cardSearchService.returnCardAsJson(dbCard);
        } else {
            apiCard = preparer.prepareCard(apiCard);
            return cardSearchService.returnCardAsJson(apiCard);
        }
    }

    @GetMapping("/id/{id}")
    public String getCardDetailsById(@PathVariable String id) throws JsonProcessingException {
        log.info("Received GET for /id/{}", id);

        var apiCard = cardSearchService.searchCardById(id);
        var dbCard = cardDBSearchService.getCardById(id);
        if (Objects.nonNull(dbCard)) {
            dbCard = preparer.prepareCard(dbCard);
            return cardSearchService.returnCardAsJson(dbCard);
        } else {
            apiCard = preparer.prepareCard(apiCard);
            return cardSearchService.returnCardAsJson(apiCard);
        }
    }
}
