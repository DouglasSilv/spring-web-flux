package com.douglas.netflix.controller;

import com.douglas.netflix.model.Movie;
import com.douglas.netflix.model.MovieEvent;
import com.douglas.netflix.repository.NetflixRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/netflix")
public class MovieController {

    private NetflixRepository repository;

    public MovieController(NetflixRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Movie> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Movie> findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Movie> save(@RequestBody Movie movie) {
        return repository.save(movie);
    }

    @PutMapping("/{id}")
    public Mono<Movie> update(@PathVariable(value = "id") String id, @RequestBody Movie movie) {
        return repository.findById(id)
                .flatMap(existingMovie -> {
                    existingMovie.setName(movie.getName());
                    existingMovie.setType(movie.getType());
                    existingMovie.setPrincipalActor(movie.getPrincipalActor());
                    return repository.save(existingMovie);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return repository.findById(id)
                .flatMap(existingMovie ->
                        repository.delete(existingMovie));
    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> getEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(val -> new MovieEvent(val, "Evento da Netflix")
                );
    }
}
