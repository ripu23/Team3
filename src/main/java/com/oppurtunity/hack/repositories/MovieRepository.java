package com.oppurtunity.hack.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oppurtunity.hack.entities.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{

}
