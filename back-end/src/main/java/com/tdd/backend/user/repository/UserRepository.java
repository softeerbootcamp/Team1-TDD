package com.tdd.backend.user.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdd.backend.user.data.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	@Query("SELECT id, email, user_name, phone_number, user_password FROM users u WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);

	Long countByEmail(String email);

	@Query("SELECT email FROM users WHERE id = :id")
	Optional<String> findEmailById(@Param("id") Long id);
}
