package com.javi.martin.MyMTGLibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update-card")
public class CardSaveController {

    @GetMapping
    public boolean saveCard(String cardName, int quantity) {
        return false;
    }
}
