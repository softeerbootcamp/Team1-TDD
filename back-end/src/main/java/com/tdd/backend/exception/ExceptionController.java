package com.tdd.backend.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tdd.backend.user.exception.DuplicateEmailException;
import com.tdd.backend.user.exception.UnauthorizedException;
import com.tdd.backend.user.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.code(HttpStatus.BAD_REQUEST.toString())
			.errorMessage("잘못된 요청입니다.")
			.build();

		List<FieldError> fieldErrors = ex.getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			errorResponse.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return errorResponse;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DuplicateEmailException.class)
	public ErrorResponse duplicateEmailExceptionHandler(DuplicateEmailException ex) {
		return ErrorResponse.builder()
			.code(HttpStatus.BAD_REQUEST.toString())
			.errorMessage(ex.getMessage())
			.build();

	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UserNotFoundException.class)
	public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException ex) {
		return ErrorResponse.builder()
			.code(HttpStatus.UNAUTHORIZED.toString())
			.errorMessage(ex.getMessage())
			.build();

	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ErrorResponse unauthorizedExceptionHandler(UnauthorizedException ex) {
		return ErrorResponse.builder()
			.code(HttpStatus.UNAUTHORIZED.toString())
			.errorMessage(ex.getMessage())
			.build();

	}
}
