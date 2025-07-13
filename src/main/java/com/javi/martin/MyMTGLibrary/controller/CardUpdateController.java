package com.javi.martin.MyMTGLibrary.controller;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.service.CardDBSaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update-card")
public class CardUpdateController {

    @Autowired
    private CardDBSaverService cardDBSaverService;

    @PostMapping
    public String saveCard(@RequestBody MTGCardDTO card,
                            @RequestParam(name = "copies") int copies) {
        if (copies > 0) {
            cardDBSaverService.upsertCard(card, copies);
        } else {
            cardDBSaverService.deleteCard(card);
        }
        return "Card updatedSuccessfully";
    }
}
