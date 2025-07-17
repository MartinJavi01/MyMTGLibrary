package com.javi.martin.MyMTGLibrary.service;

import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.*;

@Service
public class CardUIPreparerService {

    public MTGCardDTO prepareCard(MTGCardDTO cardToPrepare) {
        MTGCardDTO finalCard = cardToPrepare;

        finalCard.setOracleText(getNewLineUIDescription(finalCard.getOracleText()));
        finalCard.setOracleText(parseSymbolsToImage(finalCard.getOracleText()));
        finalCard.setManaCost(prepareManaCost(finalCard.getManaCost()));
        finalCard.setCmc(finalCard.getCmc().substring(0,1));

        if (!finalCard.getColorIdentity().isEmpty()) {
            finalCard.setColorIdentity(prepareColorsString(finalCard.getColorIdentity()));
        } else {
            var colorlessList = new ArrayList<String>();
            colorlessList.add("Colorless");
            finalCard.setColorIdentity(colorlessList);
        }

        finalCard.getPrices().setUsd(getCostString(finalCard.getPrices().getUsd()));
        finalCard.getPrices().setEur(getCostString(finalCard.getPrices().getEur()));

        finalCard.setRarity(prepareRarity(finalCard.getRarity()));
        finalCard.getSetDTO().setImageUri(prepareSetUriImage(finalCard.getSetDTO().getImageUri()));

        return finalCard;
    }

    private String getNewLineUIDescription(String baseDescription) {
        var splitedDescription = baseDescription.split("\\r?\\n");
        for (int i = 0; i < splitedDescription.length; i++) {
            if (splitedDescription[i].charAt(splitedDescription[i].length() - 1) != '.'
                && splitedDescription[i].charAt(splitedDescription[i].length() - 1) != ')') {
                splitedDescription[i] += ".";
            }
        }
        return String.join("<br><br>", splitedDescription);
    }

    private String parseSymbolsToImage(String baseDescription) {
        var returnString = "";
        var splitedDescription = baseDescription.split("}");

        for (int i = 0; i < splitedDescription.length - 1; i++) {
            var insideKeysText = splitedDescription[i].split("\\{");
            if (!insideKeysText[0].isEmpty()) {
                returnString += insideKeysText[0];
            }
            returnString += "<img class=\"symbolImage\" src=\"" + getSymbolApiUrl(insideKeysText[1]) + "\"/>";
        }

        returnString += splitedDescription[splitedDescription.length - 1];

        return returnString;
    }

    private String getSymbolApiUrl(String symbol) {
        return SCRYFALL_SVGS_BASE_PATH + SYMBOL_PATH + symbol.replace("/","") + SVG_EXTENSION;
    }

    private String prepareManaCost(String baseManaCost) {
        var finalManaCost = "";
        var splitedColors = baseManaCost.split("\\{");
        for (int i = 1; i < splitedColors.length; i ++) {
            finalManaCost += "<img class=\"symbolImage\" src=\"" +
                    getSymbolApiUrl(splitedColors[i].replace("}", "")) + "\"/>";
        }

        return finalManaCost;
    }

    private List<String> prepareColorsString(List<String> colors) {
        var finalString = "";
        var colorsList = new ArrayList<String>();
        for (int i = 0; i < colors.size(); i++) {
            finalString += "<img class=\"symbolImage\" src=\"" + getSymbolApiUrl(colors.get(i)) + "\"/>";
        }
        colorsList.add(finalString);
        return colorsList;
    }

    private String getCostString(String s) {
        return Objects.nonNull(s) ? s : "N/A";
    }

    private String prepareRarity(String rarity) {
        return rarity.substring(0, 1).toUpperCase() + rarity.substring(1);
    }

    private String prepareSetUriImage(String setImageUri) {
        return "<img class=\"setImage\" src=\"" + setImageUri + "\"/>";
    }
}
