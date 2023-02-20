package com.tdd.backend.mypage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdd.backend.mypage.data.MyCarResponse;
import com.tdd.backend.mypage.model.MyCar;
import com.tdd.backend.post.model.Option;

@Repository
public interface MyCarRepository extends CrudRepository<MyCar, Long> {

	@Query("SELECT car_id FROM mycars WHERE id = :id")
	Optional<Long> findCarIdById(@Param("id") Long id);

	@Query("SELECT o.name, o.category "
		+ "FROM options o "
		+ "WHERE o.mycar_id = :myCarId"
	)
	List<Option> findOptionsByMyCarId(
		@Param("myCarId") Long myCarId
	);

	@Query("SELECT DISTINCT c.car_name, m.id "
		+ "FROM mycars m "
		+ "JOIN cars c ON m.car_id = c.id "
		+ "JOIN users u ON m.user_id = u.id "
		+ "WHERE u.id = :userId"
	)
	List<MyCarResponse> getCarInfoByUserId(@Param("userId") Long userId);
}
