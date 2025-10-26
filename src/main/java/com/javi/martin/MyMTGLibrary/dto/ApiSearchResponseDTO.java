package com.javi.martin.MyMTGLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ApiSearchResponseDTO {

    @JsonProperty("total_cards")
    private int totalCards;
    @JsonProperty("has_more")
    private boolean hasMore;
    @JsonProperty("next_page")
    private String nextPage;
    @JsonProperty("data")
    private List<MTGCardDTO> data;
}
