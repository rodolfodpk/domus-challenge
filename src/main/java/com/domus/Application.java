package com.domus;

import com.domus.model.IDirectorsService;
import com.domus.model.IMoviesRepository;
import com.domus.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Service
    public static class DirectorsService implements IDirectorsService {

        private final IMoviesRepository moviesRepository;

        @Autowired
        public DirectorsService(IMoviesRepository moviesRepository) {
            this.moviesRepository = moviesRepository;
        }

        public Flux<String> getDirectorsWithAtLeastMovies(int threshold) {
            return moviesRepository.fetchAllMovies()
                    .groupBy(Movie::director)
                    .flatMap(group -> group.count()
                            .filter(count -> count >= threshold)
                            .map(count -> group.key()))
                    .sort();
        }
    }
}
