package com.domus;

import com.domus.model.IMoviesRepository;
import com.domus.model.Movie;
import com.domus.service.DirectorsService;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DirectorsServiceTest {

    @Test
    public void getDirectorsWithAtLeastMovies() {
        IMoviesRepository moviesRepository = mock(IMoviesRepository.class);
        DirectorsService movieService = new DirectorsService(moviesRepository);

        Movie movie1 = new Movie("Movie 1", "Director A", "2023", "Id1");
        Movie movie2 = new Movie("Movie 2", "Director B", "2024", "Id2");
        Movie movie3 = new Movie("Movie 3", "Director A", "2025", "Id3");

        when(moviesRepository.fetchAllMovies()).thenReturn(Flux.fromIterable(Arrays.asList(movie1, movie2, movie3)));

        StepVerifier.create(movieService.getDirectorsWithAtLeastMovies(2))
                .expectNextCount(1)
                .verifyComplete();

        verify(moviesRepository, times(1)).fetchAllMovies();
    }
}