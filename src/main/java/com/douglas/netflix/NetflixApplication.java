package com.douglas.netflix;

import com.douglas.netflix.model.Movie;
import com.douglas.netflix.repository.NetflixRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class NetflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, NetflixRepository repository) {
		return args -> {
			Flux<Movie> movieFlux = Flux.just(
					new Movie(null, "Interstellar", "Science fiction", "Matthew McConaughey", 2014),
					new Movie(null, "The Woman in the Window", "Psychological thriller", "Amy Adams", 2021),
					new Movie(null, "Seaspiracy", "Documentary", "Ali Tabrizi", 2021),
					new Movie(null, "El Camino: A Breaking Bad Movie", "Neo-Western", "Aaron Paul", 2019),
					new Movie(null, "Shrek 2", "Comedy", "Andrew Adamson", 2004)
			).flatMap(repository::save);

			movieFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}
