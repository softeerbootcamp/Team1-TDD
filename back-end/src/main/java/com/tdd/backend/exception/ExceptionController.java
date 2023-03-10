package com.tdd.backend.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tdd.backend.auth.exception.ExpiredATKException;
import com.tdd.backend.auth.exception.InvalidTokenException;
import com.tdd.backend.car.exception.CarNotFoundException;
import com.tdd.backend.mail.exception.EmailNotFoundException;
import com.tdd.backend.post.exception.ApprovalFailException;
import com.tdd.backend.user.exception.DuplicateEmailException;
import com.tdd.backend.user.exception.UnauthorizedException;
import com.tdd.backend.user.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionController {

	@ResponseStatus(HttpStatus.FOUND)
	@ExceptionHandler(ExpiredATKException.class)
	public ErrorResponse expiredATKExceptionHandler(ExpiredATKException ex) {
		return ErrorResponse.builder()
			.code(HttpStatus.FOUND.toString())
			.errorMessage(ex.getMessage())
			.build();
	}

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

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidTokenException.class)
	public ErrorResponse invalidTokenExceptionHandler(InvalidTokenException ex) {
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

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CarNotFoundException.class)
	public ErrorResponse carNotFoundExceptionHandler(CarNotFoundException ex) {
		return ErrorResponse.builder()
			.code(HttpStatus.BAD_REQUEST.toString())
			.errorMessage(ex.getMessage())
			.build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmailNotFoundException.class)
	public ErrorResponse emailNotFoundExceptionHandler(EmailNotFoundException ex) {
		return ErrorResponse.builder()
			.code(HttpStatus.BAD_REQUEST.toString())
			.errorMessage(ex.getMessage())
			.build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ApprovalFailException.class)
	public ErrorResponse approvalFailExceptionHandler(ApprovalFailException ex) {
		return ErrorResponse.builder()
			.code(HttpStatus.BAD_REQUEST.toString())
			.errorMessage(ex.getMessage())
			.build();
	}
}
