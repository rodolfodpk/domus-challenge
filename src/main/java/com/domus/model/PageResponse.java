package com.domus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PageResponse(
        @JsonProperty("page") int page,
        @JsonProperty("per_page") int perPage,
        @JsonProperty("total") int total,
        @JsonProperty("total_pages") int totalPages,
        @JsonProperty("data") List<Movie> data) {
}
