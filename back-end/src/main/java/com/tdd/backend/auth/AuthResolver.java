package com.tdd.backend.auth;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tdd.backend.user.data.UserSession;
import com.tdd.backend.user.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

//todo : 로그인 유지 기술 및 테스트 코드 구현
@Slf4j
public class AuthResolver implements HandlerMethodArgumentResolver {

	// @Value("${jwt.token.secret-key:secret-key}")
	private final String KEY = "JFbPbHB/8Oz2CSK4q0sAHrRkr4Hs9MYwKkMY4Jf97+0=";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		boolean isAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		boolean isUserSession = parameter.getParameterType().equals(UserSession.class);

		return isAnnotation && isUserSession;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String jws = webRequest.getHeader("Authorization");

		if (jws == null || jws.equals("")) {
			throw new UnauthorizedException();
		}

		byte[] decodedKey = Base64.decodeBase64(KEY);

		try {
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(decodedKey).build().parseClaimsJws(jws);

			String userId = claims.getBody().getSubject();
			return UserSession.builder().userId(Long.parseLong(userId)).build();
		} catch (JwtException e) {
			throw new UnauthorizedException();
		}
	}
}
