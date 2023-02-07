package com.tdd.backend.user;

import java.net.URI;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.user.data.Session;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.exception.DuplicateEmailException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@Operation(summary = "유저 회원가입 요청", description = "User SignUp request")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "302", description = "REDIRECT"),
		@ApiResponse(responseCode = "400", description = "BAD REQUEST"),
		@ApiResponse(responseCode = "404", description = "NOT FOUND"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("/users")
	public ResponseEntity<Void> signup(@RequestBody @Valid UserCreate userCreate) {
		userService.save(userCreate);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/"));
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@GetMapping("/users/validation/{email}}")
	public void userEmailValidCheck(@PathVariable String email) {
		if (userService.isDuplicateEmail(email)) {
			throw new DuplicateEmailException();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody @Valid UserLogin userLogin, HttpSession httpSession) {
		String accessToken = userService.signIn(userLogin);

		httpSession.setAttribute("AccessToken", accessToken);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/"));
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@RequestMapping("/auth")
	public void test(@LoginUser Session session) {

		User user = session.getUser();
		log.info(">> user : {}", user.getUserName());
	}
}
