package com.javi.martin.MyMTGLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MTGSetDTO {

    @JsonProperty("id")
    private String setId;
    @JsonProperty("name")
    private String setName;
    @JsonProperty("set_type")
    private String setType;
    @JsonProperty("icon_svg_uri")
    private String imageUri;
}
