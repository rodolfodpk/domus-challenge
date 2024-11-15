package com.domus.model;

import reactor.core.publisher.Flux;

public interface IDirectorsService {
    Flux<String> getDirectorsWithAtLeastMovies(int threshold);
}
