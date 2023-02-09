package com.tdd.backend.option.render;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RenderRepository {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public RenderRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<RenderOption> getCarIdByCarName(Long carId) {
		return jdbcTemplate.query(
			"SELECT E.option_name, E.category_name  "
				+ "FROM entire_options AS E "
				+ "JOIN car_options AS C "
				+ "ON (E.id = C.entire_option_id) "
				+ "WHERE C.car_id = ?",
			renderRowMapper(), carId);
	}

	private RowMapper<RenderOption> renderRowMapper() {
		return (rs, rowNum) -> new RenderOption(
			rs.getString("option_name"),
			rs.getString("category_name")
		);
	}

}
