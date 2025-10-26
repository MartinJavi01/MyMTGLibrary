package com.javi.martin.MyMTGLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Document(collection = "Cards")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MTGCardDTO {

    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("oracle_id")
    private String oracleId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("released_at")
    private Date releasedAt;
    @JsonProperty("image_uris")
    private ImageUrisDTO imageUris;
    @JsonProperty("mana_cost")
    private String manaCost;
    @JsonProperty("cmc")
    private String cmc;
    @JsonProperty("type_line")
    private String typeLine;
    @JsonProperty("oracle_text")
    private String oracleText;
    @JsonProperty("power")
    private String power;
    @JsonProperty("toughness")
    private String toughness;
    @JsonProperty("colors")
    private List<String> colors;
    @JsonProperty("color_identity")
    private List<String> colorIdentity;
    @JsonProperty("keywords")
    private List<String> keywords;
    @JsonProperty("rarity")
    private String rarity;
    @JsonProperty("set")
    private String set;
    @JsonProperty("set_id")
    private String setId;
    @JsonProperty("set_name")
    private String setName;
    @JsonProperty("prices")
    private PricesDTO prices;
    @JsonProperty("purchase_uris")
    private PurchaseUrisDTO purchaseUris;
    @JsonProperty("card_faces")
    private List<CardFaceDTO> cardFaces;

    private MTGSetDTO setDTO;
    private int copies;
    private boolean foil;
    private String spellType;
    private String subType;

    public MTGCardDTO() {
        copies = 0;
        foil = false;
    }
}
