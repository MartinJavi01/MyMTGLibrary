package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.service.CardSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CardSearchService cardSearchService;

    @GetMapping("{cardName}")
    public String test(@PathVariable String cardName) {
        return cardSearchService.getCardsLol(cardName);
    }
}
