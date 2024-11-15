package com.domus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Movie(@JsonProperty("Title") String title,
                    @JsonProperty("Director") String director,
                    @JsonProperty("Year") String year,
                    @JsonProperty("ImdbID") String imdbID) {
}
