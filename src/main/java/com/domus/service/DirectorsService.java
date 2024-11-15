package com.domus.service;

import com.domus.model.IDirectorsService;
import com.domus.model.IMoviesRepository;
import com.domus.model.Movie;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DirectorsService implements IDirectorsService {
    private final IMoviesRepository moviesRepository;

    public DirectorsService(IMoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Flux<String> getDirectorsWithAtLeastMovies(int threshold) {
        return moviesRepository.fetchAllMovies()
                .groupBy(Movie::director)
                .flatMap(group -> group.count()
                        .filter(count -> count >= threshold)
                        .map(count -> group.key()))
                .sort();
    }
}