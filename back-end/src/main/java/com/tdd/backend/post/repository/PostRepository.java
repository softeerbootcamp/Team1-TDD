package com.tdd.backend.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdd.backend.mail.data.PostInfo;
import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.Location;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	@Query("SELECT latitude, longitude FROM locations WHERE post_id = :id")
	Optional<Location> findLocationByPostId(@Param("id") Long id);

	@Query("SELECT o.name, o.category FROM options o WHERE o.mycar_id = :myCarID")
	List<Option> findOptionsByMyCarId(@Param("myCarID") Long myCarId);

	@Query("SELECT id, date, status FROM appointments WHERE post_id = :id")
	List<Appointment> findAppointmentsByPostId(@Param("id") Long id);

	@Modifying
	@Query("UPDATE appointments SET status = 'ACCEPT', tester_id = :testerid WHERE id = :id")
	void updateTesterIdStatusAccept(@Param("id") Long id, @Param("testerid") Long testerId);

	@Query("SELECT DISTINCT p.id, p.ride_option, p.requirement, p.mycar_id FROM appointments a "
		+ "JOIN posts p on p.id = a.post_id "
		+ "WHERE a.tester_id = :testerId")
	List<Post> findPostByTesterId(@Param("testerId") Long testerId);

	@Query("SELECT DISTINCT p.id, p.ride_option, p.mycar_id, p.requirement FROM posts p "
		+ "JOIN appointments a ON p.id = a.post_id "
		+ "JOIN mycars m ON p.mycar_id = m.id "
		+ "JOIN users u ON m.user_id = u.id "
		+ "WHERE u.id = :userId "
	)
	List<Post> findPostByUserId(
		@Param("userId") Long userId
	);

	@Query("SELECT date FROM appointments WHERE post_id = :postId and tester_id = :userId")
	Optional<String> findDateByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);

	@Query("SELECT c.car_name, p.requirement, u.id as user_id "
		+ "FROM appointments a "
		+ "JOIN posts p ON a.post_id = p.id "
		+ "JOIN mycars m ON p.mycar_id = m.id "
		+ "JOIN cars c ON m.car_id = c.id "
		+ "JOIN users u ON m.user_id = u.id "
		+ "WHERE a.id = :appointmentId"
	)
	Optional<PostInfo> findPostInfoByAppointmentId(@Param("appointmentId") Long appointmentId);

	@Query("SELECT p.id FROM posts p "
		+ "JOIN options o ON p.mycar_id = o.mycar_id "
		+ "JOIN mycars m ON p.mycar_id = m.id "
		+ "JOIN cars c ON m.car_id = c.id "
		+ "JOIN appointments a ON p.id = a.post_id "
		+ "WHERE o.name IN (:options) "
		+ "AND c.id = :carId "
		+ "AND a.date IN (:dates) "
		+ "AND a.status = 'PENDING'"
		+ "GROUP BY p.id HAVING COUNT(DISTINCT o.name) = :count"
	)
	List<Long> findPostIdsByOptionsAndDatesAndCarId(
		@Param("options") List<String> options,
		@Param("dates") List<String> dates,
		@Param("carId") Long carId,
		@Param("count") int count
	);

	@Query("SELECT p.id FROM posts p "
		+ "JOIN car_options co ON p.mycar_id = co.car_id "
		+ "JOIN options o ON p.mycar_id = o.mycar_id "
		+ "JOIN mycars m ON p.mycar_id = m.id "
		+ "JOIN cars c ON m.car_id = c.id "
		+ "JOIN appointments a ON p.id = a.post_id "
		+ "WHERE o.name IN (:options) "
		+ "AND c.id = :carId "
		+ "AND a.status = 'PENDING'"
		+ "GROUP BY p.id HAVING COUNT(DISTINCT o.name) = :count"
	)
	List<Long> findPostIdsByOptionsAndCarId(
		@Param("options") List<String> options,
		@Param("carId") Long carId,
		@Param("count") int count
	);

	@Query("SELECT p.id FROM posts p "
		+ "JOIN appointments a on p.id = a.post_id "
		+ "JOIN mycars m on p.mycar_id = m.id "
		+ "WHERE a.date IN (:dates) "
		+ "AND m.car_id = :carId "
		+ "AND a.status = 'PENDING' "
		+ "GROUP BY p.id"
	)
	List<Long> findPostIdsByDatesAndCarId(
		@Param("dates") List<String> dates,
		@Param("carId") Long carId
	);

	@Query("SELECT p.id FROM posts p "
		+ "JOIN appointments a on p.id = a.post_id "
		+ "JOIN mycars m on p.mycar_id = m.id "
		+ "WHERE m.car_id = :carId "
		+ "AND a.status = 'PENDING' "
		+ "GROUP BY p.id"
	)
	List<Long> findPostIdsByCarId(
		@Param("carId") Long carId
	);
}
