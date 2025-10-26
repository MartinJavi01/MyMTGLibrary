package com.javi.martin.MyMTGLibrary.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javi.martin.MyMTGLibrary.dto.ApiSearchResponseDTO;
import com.javi.martin.MyMTGLibrary.dto.MTGCardDTO;
import com.javi.martin.MyMTGLibrary.dto.MTGSetDTO;
import com.javi.martin.MyMTGLibrary.utils.ParametersStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.javi.martin.MyMTGLibrary.constants.MyMTGLibraryConstants.*;

@Service
public class CardApiSearchService {

    private ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(CardApiSearchService.class);

    public CardApiSearchService() {
        objectMapper = new ObjectMapper();
    }

    public ApiSearchResponseDTO searchCardByName(String searchString) {
        var requestParams = new HashMap<String, String>();
        requestParams.put("q", searchString);

        var client = HttpClient.newHttpClient();

        ApiSearchResponseDTO responseObject;

        try {
            log.info("Performing api call to {}", SCRYFALL_BASE_PATH + SEARCH_PATH + ParametersStringBuilder.getParamsString(requestParams));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SCRYFALL_BASE_PATH + SEARCH_PATH + ParametersStringBuilder.getParamsString(requestParams))).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.debug("Received response: {}", response);
            responseObject = objectMapper.readValue(response.body(), new TypeReference<ApiSearchResponseDTO>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(responseObject.getData().size() > 20) {
            responseObject.setData(getReducedDataList(responseObject));
        }

        return responseObject;
    }

    private List<MTGCardDTO> getReducedDataList(ApiSearchResponseDTO responseObject) {
        var newList = new ArrayList<MTGCardDTO>();
        for (int i = 0; i < 20; i++) {
            newList.add(responseObject.getData().get(i));
        }
        return newList.stream().toList();
    }

    public MTGCardDTO searchCardById(String id) {
        var client = HttpClient.newHttpClient();
        var card = new MTGCardDTO();

        try {
            log.info("Performing api call to {}", SCRYFALL_BASE_PATH + ID_PATH + id);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SCRYFALL_BASE_PATH + ID_PATH + id)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.debug("Received response: {}", response);
            card = objectMapper.readValue(response.body(), new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        card.setSetDTO(getSetForCard(card));
        card.setSpellType(getSpellType(card));
        card.setSubType(getSpellSubType(card));

        return card;
    }

    public MTGSetDTO getSetForCard(MTGCardDTO card) {
        var client = HttpClient.newHttpClient();
        MTGSetDTO returnSet;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SCRYFALL_BASE_PATH + SETS_PATH + card.getSet())).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            returnSet = objectMapper.readValue(response.body(), MTGSetDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return returnSet;
    }

    public String getSpellType(MTGCardDTO card) {
        if (card.getTypeLine().contains(TYPE_LINE_SEPARATOR)) {
            return card.getTypeLine().split(TYPE_LINE_SEPARATOR)[0];
        } else {
            return card.getTypeLine();
        }
    }

    public String getSpellSubType(MTGCardDTO card) {
        if (card.getTypeLine().contains(TYPE_LINE_SEPARATOR)) {
            return card.getTypeLine().split(TYPE_LINE_SEPARATOR)[1];
        } else {
            return null;
        }
    }
}
