package com.javi.martin.MyMTGLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CardFaceDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mana_cost")
    private String manaCost;
    @JsonProperty("type_line")
    private String typeLine;
    @JsonProperty("oracle_text")
    private String oracleText;
    @JsonProperty("colors")
    private List<String> colors;
    @JsonProperty("defense")
    private String defense;
    @JsonProperty("power")
    private String power;
    @JsonProperty("toughness")
    private String toughness;
    @JsonProperty("image_uris")
    private ImageUrisDTO imageUris;

}
