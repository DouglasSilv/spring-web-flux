package com.douglas.netflix.repository;

import com.douglas.netflix.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetflixRepository extends ReactiveMongoRepository<Movie, String> {
}
