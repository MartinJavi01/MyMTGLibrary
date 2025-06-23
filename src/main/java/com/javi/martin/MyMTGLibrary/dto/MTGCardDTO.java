package com.javi.martin.MyMTGLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Document(collection = "Cards")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MTGCardDTO {

    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("oracle_id")
    private String oracleId;
    @JsonProperty("cardmarket_id")
    private int cardmarketId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("released_at")
    private Date released_at;
    @JsonProperty("image_uris")
    private ImageUris image_uris;
    @JsonProperty("cmc")
    private int cmc;
    @JsonProperty("type_line")
    private String type_line;
    @JsonProperty("oracle_text")
    private String oracle_text;
    @JsonProperty("power")
    private int power;
    @JsonProperty("toughness")
    private int toughness;
    @JsonProperty("colors")
    private List<String> colors;
    @JsonProperty("colorIdentity")
    private List<String> colorIdentity;
    @JsonProperty("keywords")
    private List<String> keywords;
    @JsonProperty("set")
    private String set;
    @JsonProperty("set_id")
    private String set_id;
    @JsonProperty("set_name")
    private String set_name;

    private int quantity;

    public MTGCardDTO() {
        quantity = 1;
    }
}
