package com.domus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DirectorsApiE2eTest {

    @Autowired
    ObjectMapper objectMapper;
    @LocalServerPort
    private int port;
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'Director 1, Director 2, Director 3, Director 4, Director 5'",
            "2, 'Director 2, Director 3, Director 4, Director 5'",
            "3, 'Director 3, Director 4, Director 5'",
            "4, 'Director 4, Director 5'",
            "5, 'Director 5'",
            "6, ''",
    })
    void testGetDirectorsWithAtLeastMovies(Integer threshold, String directors) {

        // Parse the director String to a List using the parse method
        List<String> expectedDirectors = Arrays.stream(directors.split(",")).map(String::trim).toList();

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/directors").queryParam("threshold", threshold).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(json -> {
                    List<String> directorList = parse(json);
                    assertEquals(expectedDirectors.toString(), directorList.toString());
                });
    }

    private List<String> parse(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}