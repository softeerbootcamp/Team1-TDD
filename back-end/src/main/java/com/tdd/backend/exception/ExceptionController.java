package com.tdd.backend.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tdd.backend.user.exception.DuplicateEmailException;

@RestControllerAdvice
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.code("400")
			.errorMessage("잘못된 요청입니다.")
			.build();

		List<FieldError> fieldErrors = e.getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			errorResponse.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return errorResponse;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DuplicateEmailException.class)
	public ErrorResponse userNotFoundExceptionHandler(DuplicateEmailException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.code(HttpStatus.BAD_REQUEST.toString())
			.errorMessage(e.getMessage())
			.build();

		errorResponse.addValidation("email", "이메일에 해당하는 유저가 이미 존재합니다.");

		return errorResponse;

	}
}
