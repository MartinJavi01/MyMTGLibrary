package com.javi.martin.MyMTGLibrary.service;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CardUIPreparer {

    public MTGCardDTO prepareCard(MTGCardDTO cardToPrepare) {
        MTGCardDTO finalCard = cardToPrepare;

        var splitedDescription = cardToPrepare.getOracleText().split("\\.");
        finalCard.setOracleText(String.join(".<br>", splitedDescription));

        finalCard.setTypeLine(addPreBlankSpace(finalCard.getTypeLine(), "Card type:"));
        finalCard.setCmc(addPreBlankSpace(finalCard.getCmc(), "Cmc:"));
        finalCard.setOracleText(addPreBlankSpace(cardToPrepare.getOracleText(), "Description"));

        return finalCard;
    }

    private String addPreBlankSpace(String s, String preString) {
        var spaces = "";
        if (preString.length() < 15) {
            spaces = "&nbsp;".repeat(15 - preString.length());
        }
        return spaces + s;
    }
}
