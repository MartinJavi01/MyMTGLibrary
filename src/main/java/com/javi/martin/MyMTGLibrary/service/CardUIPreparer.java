package com.javi.martin.MyMTGLibrary.service;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.*;


//http://eakett.ca/mtgimage/
@Service
public class CardUIPreparer {

    public MTGCardDTO prepareCard(MTGCardDTO cardToPrepare) {
        MTGCardDTO finalCard = cardToPrepare;

        var splitedDescription = cardToPrepare.getOracleText().split("\\.");
        finalCard.setOracleText(String.join(".<br>", splitedDescription));

        finalCard.setOracleText(getUIDescription(finalCard.getOracleText()));

        return finalCard;
    }

    private String getUIDescription(String baseDescription) {
        var returnString = "";
        var splitedDescription = baseDescription.split("}");

        for (int i = 0; i < splitedDescription.length - 1; i++) {
            var insideKeysText = splitedDescription[i].split("\\{");
            if (!insideKeysText[0].isEmpty()) {
                returnString += insideKeysText[0];
            }
            returnString += "<img class=\"symbolImage\" src=\"" + getSymbolApiUrl(insideKeysText[1].toLowerCase()) + "\"/>";
        }

        returnString += splitedDescription[splitedDescription.length - 1];

        return returnString;
    }

    private String getSymbolApiUrl(String symbol) {
        if ((!symbol.contains("b") && !symbol.contains("g") && !symbol.contains("w")
                && !symbol.contains("r") && !symbol.contains("u")) && !StringUtils.isNumeric(symbol)) {
            return SYMBOL_API_BASE_URL + OTHER_PATH + symbol + SVG_EXTENSION;
        } else {
            return SYMBOL_API_BASE_URL + MANA_PATH + symbol.replace("/","") + SVG_EXTENSION;
        }
    }
}
