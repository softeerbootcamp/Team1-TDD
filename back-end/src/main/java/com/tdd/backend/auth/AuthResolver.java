package com.tdd.backend.auth;

import static com.tdd.backend.auth.util.JwtTokenProvider.JwtTokenRole.*;
import static com.tdd.backend.auth.util.JwtTokenProvider.JwtTokenStatus.*;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tdd.backend.auth.exception.InvalidTokenException;
import com.tdd.backend.auth.util.JwtTokenProvider;
import com.tdd.backend.user.data.UserToken;
import com.tdd.backend.user.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthResolver implements HandlerMethodArgumentResolver {

	private final JwtTokenProvider jwtTokenProvider;

	public AuthResolver(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info(">>> param : {}", parameter.getParameterType());
		boolean isAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		boolean isUserSession = parameter.getParameterType().equals(UserToken.class);

		return isAnnotation && isUserSession;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String jws = webRequest.getHeader("Authorization");

		if (jws == null || jws.equals("")) {
			throw new UnauthorizedException();
		}

		if (jwtTokenProvider.validateToken(jws).equals(ACCESS)) {
			if (!jwtTokenProvider.getRoleFromJwt(jws).equals(ATK)) {
				throw new InvalidTokenException();
			}

			Long idFromJwt = jwtTokenProvider.getUserIdFromJwt(jws);
			return UserToken.builder()
				.id(idFromJwt)
				.build();

		}
		throw new UnauthorizedException();

	}
}
