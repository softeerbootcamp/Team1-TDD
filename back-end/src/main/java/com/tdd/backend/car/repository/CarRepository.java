package com.tdd.backend.car.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdd.backend.car.model.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
	@Query("SELECT c.id FROM cars c WHERE c.car_name = :name")
	Optional<Long> findIdByCarName(@Param("name") String name);

	@Query("SELECT c.car_name, c.car_kor_name, c.car_image_url FROM cars c")
	List<Car> findAllCar();

	@Query("SELECT c.id, c.car_name, c.car_kor_name, c.car_image_url "
		+ "FROM cars c "
		+ "JOIN mycars m ON c.id = m.car_id "
		+ "JOIN posts p ON m.id = p.mycar_id "
		+ "WHERE p.id = :postId"
	)
	Optional<Car> findCarByPostId(@Param("postId") Long postId);
}
