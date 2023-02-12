package com.tdd.backend.option.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdd.backend.option.model.Option;

@Repository
public interface RenderRepository extends CrudRepository<Option, Long> {
	@Query("select o.* from entire_options o join car_options co on o.id = co.entire_option_id where co.car_id =:carId")
	List<Option> getCarOptionList(@Param("carId") Long carId);
}
