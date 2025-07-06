package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.service.CardDBSaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update-card")
public class CardSaveController {

    @Autowired
    private CardDBSaverService cardDBSaverService;

    @GetMapping
    public String saveCard(@RequestParam(name = "cardName") MTGCardDTO card,
                            @RequestParam(name = "copies") int copies) {
        cardDBSaverService.saveCard(card, copies);
        return "Card updatedSuccessfully";
    }
}
