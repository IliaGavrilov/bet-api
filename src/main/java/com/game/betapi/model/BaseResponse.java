package com.game.betapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BaseResponse {
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("errorMessage")
    private String errorMessage;
}
