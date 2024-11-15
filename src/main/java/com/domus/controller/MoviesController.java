package com.domus.controller;

import com.domus.model.Movie;
import com.domus.model.PageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final List<Movie> movies;

    public MoviesController() throws IOException {
        File file = new ClassPathResource("movies.json").getFile();
        ObjectMapper mapper = new ObjectMapper();
        Movie[] moviesArray = mapper.readValue(file, Movie[].class);
        this.movies = Arrays.asList(moviesArray);
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PageResponse> getMovies(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        int start = (page - 1) * size;
        int end = Math.min((start + size), movies.size());
        List<Movie> moviesPage = movies.subList(start, end);
        int totalPages = (int) Math.ceil((double) movies.size() / size);
        return Mono.just(new PageResponse(page, size, movies.size(), totalPages, moviesPage));
    }

}