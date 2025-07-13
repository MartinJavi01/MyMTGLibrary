package com.javi.martin.MyMTGLibrary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.utils.ParametersStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.SCRYFALL_NAMED_PATH;

@Service
public class CardSearchService {

    @Autowired
    private CardDBSaverService cardDBSaverService;

    private ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(CardSearchService.class);

    public CardSearchService() {
        objectMapper = new ObjectMapper();
    }

    public MTGCardDTO searchCardByName(String cardName) {
        var requestParams = new HashMap<String, String>();
        requestParams.put("fuzzy", cardName);
        requestParams.put("format", "json");

        var client = HttpClient.newHttpClient();
        MTGCardDTO returnCard;

        try  {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SCRYFALL_NAMED_PATH + ParametersStringBuilder.getParamsString(requestParams))).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            returnCard = objectMapper.readValue(response.body(), MTGCardDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return returnCard;
    }

    public String returnCardAsJson(MTGCardDTO cardDTO) throws JsonProcessingException {
        return objectMapper.writeValueAsString(cardDTO);
    }
}
