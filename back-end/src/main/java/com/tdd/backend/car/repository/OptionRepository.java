package com.tdd.backend.car.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdd.backend.car.model.Option;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {
	@Query("select o.* from entire_options o join car_options co on o.id = co.entire_option_id where co.car_id =:carId")
	List<Option> findCarOptionList(@Param("carId") Long carId);
}
