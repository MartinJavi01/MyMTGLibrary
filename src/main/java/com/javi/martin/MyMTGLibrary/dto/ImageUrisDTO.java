package com.javi.martin.MyMTGLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ImageUrisDTO {

    @JsonProperty("border_crop")
    private String borderCrop;
}
