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

	@Query("SELECT id, ride_option, car_name, user_id, requirement FROM  posts WHERE user_id = :id")
	List<Post> findPostByUserId(@Param("id") Long id);

	@Query("SELECT post_id FROM appointments WHERE tester_id = :userId")
	List<Long> findPostIdByTesterId(@Param("userId") Long userId);

	@Query("SELECT date FROM appointments WHERE post_id = :postId and tester_id = :userId")
	Optional<String> findDateByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);

	@Query("SELECT p.id FROM posts p "
		+ "JOIN options o ON p.id = o.post_id "
		+ "JOIN appointments a on p.id = a.post_id "
		+ "WHERE o.name IN (:options) "
		+ "AND a.date IN (:dates) "
		+ "AND p.car_name = :carName "
		+ "AND a.status = 'PENDING' "
		+ "GROUP BY p.id HAVING COUNT(DISTINCT o.name) = :count"
	)
	List<Long> findPostIdsByOptionsAndDatesAndCarName(
		@Param("options") List<String> options,
		@Param("dates") List<String> dates,
		@Param("carName") String carName,
		@Param("count") int count
	);

	@Query("SELECT p.id FROM posts p "
		+ "JOIN options o ON p.id = o.post_id "
		+ "JOIN appointments a on p.id = a.post_id "
		+ "WHERE o.name IN (:options) "
		+ "AND p.car_name = :carName "
		+ "AND a.status = 'PENDING' "
		+ "GROUP BY p.id HAVING COUNT(DISTINCT o.name) = :count"
	)
	List<Long> findPostIdsByOptionsAndCarName(
		@Param("options") List<String> options,
		@Param("carName") String carName,
		@Param("count") int count
	);

	@Query("SELECT p.id FROM posts p "
		+ "JOIN appointments a on p.id = a.post_id "
		+ "WHERE a.date IN (:dates) "
		+ "AND p.car_name = :carName "
		+ "AND a.status = 'PENDING' "
		+ "GROUP BY p.id"
	)
	List<Long> findPostIdsByDatesAndCarName(
		@Param("dates") List<String> dates,
		@Param("carName") String carName
	);

	@Query("SELECT p.id FROM posts p "
		+ "JOIN appointments a on p.id = a.post_id "
		+ "WHERE p.car_name = :carName "
		+ "AND a.status = 'PENDING' "
		+ "GROUP BY p.id"
	)
	List<Long> findPostIdsByCarName(
		@Param("carName") String carName
	);
}
