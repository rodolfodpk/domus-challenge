package com.domus.repository;

import com.domus.config.ApiConfig;
import com.domus.model.IMoviesRepository;
import com.domus.model.Movie;
import com.domus.model.PageResponse;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class WebMoviesRepository implements IMoviesRepository {
    private final WebClient webClient;
    private final ApiConfig apiConfig;
    private final CircuitBreaker circuitBreaker;

    public WebMoviesRepository(WebClient webClient, ApiConfig apiConfig) {
        this.webClient = webClient;
        this.apiConfig = apiConfig;
        this.circuitBreaker = CircuitBreakerRegistry.ofDefaults().circuitBreaker(this.getClass().getName());
    }

    @Override
    public Flux<Movie> fetchAllMovies() {
        return fetchPage(1)
                .expand(pageResponse -> {
                    if (pageResponse.page() < pageResponse.totalPages()) {
                        return fetchPage(pageResponse.page() + 1);
                    } else {
                        return Mono.empty();
                    }
                })
                .concatMap(pageResponse -> Flux.fromIterable(pageResponse.data()));
    }

    private Mono<PageResponse> fetchPage(Integer page) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(apiConfig.scheme)
                        .host(apiConfig.host)
                        .port(apiConfig.port)
                        .path(apiConfig.path)
                        .queryParam("page", page)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PageResponse.class)
                .transform(CircuitBreakerOperator.of(circuitBreaker));
    }

}