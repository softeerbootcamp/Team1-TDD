package com.tdd.backend.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdd.backend.post.model.Location;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	@Query("SELECT latitude,longitude FROM locations WHERE post_id = :id")
	Optional<Location> findLocationByPostId(@Param("id") Long id);

	@Query("SELECT name, category FROM options WHERE  post_id = :id")
	List<Option> findOptionByPostId(@Param("id") Long id);

	@Query("SELECT  date FROM appointments WHERE post_id = :id")
	List<String> findAppointmentByPostId(@Param("id") Long id);
}
