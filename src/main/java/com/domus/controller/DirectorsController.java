package com.domus.controller;

import com.domus.model.IDirectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DirectorsController {

    private final IDirectorsService directorsService;

    @Autowired
    public DirectorsController(IDirectorsService directorsService) {
        this.directorsService = directorsService;
    }

    @GetMapping(value = "/directors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<String>> getDirectorsWithAtLeastMovies(
            @RequestParam(name = "threshold", defaultValue = "1") int threshold) {
        return directorsService.getDirectorsWithAtLeastMovies(threshold).collectList();
    }
}