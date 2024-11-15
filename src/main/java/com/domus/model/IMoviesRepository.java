package com.domus.model;

import reactor.core.publisher.Flux;

public interface IMoviesRepository {
    Flux<Movie> fetchAllMovies();
}