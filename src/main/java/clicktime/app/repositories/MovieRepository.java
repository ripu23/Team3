package clicktime.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import clicktime.app.entities.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{

}
