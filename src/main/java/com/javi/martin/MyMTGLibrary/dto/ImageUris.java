package com.javi.martin.MyMTGLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageUris {

    @JsonProperty("small")
    private String small;
    @JsonProperty("normal")
    private String normal;
    @JsonProperty("large")
    private String large;
    @JsonProperty("png")
    private String png;
    @JsonProperty("art_crop")
    private String art_crop;
    @JsonProperty("border_crop")
    private String border_crop;
}
