package com.tdd.backend.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.Location;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	@Query("SELECT latitude, longitude FROM locations WHERE post_id = :id")
	Optional<Location> findLocationByPostId(@Param("id") Long id);

	@Query("SELECT name, category FROM options WHERE  post_id = :id")
	List<Option> findOptionByPostId(@Param("id") Long id);

	@Query("SELECT id, date, status FROM appointments WHERE post_id = :id")
	List<Appointment> findAppointmentsByPostId(@Param("id") Long id);

	@Modifying
	@Query("UPDATE appointments SET status = 'ACCEPT', tester_id = :testerid WHERE id = :id")
	void updateTesterIdStatusAccept(@Param("id") Long id, @Param("testerid") Long testerId);
}
