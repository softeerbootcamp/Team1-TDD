package com.tdd.backend.car.repository;

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
}
