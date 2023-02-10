package com.tdd.backend.auth;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tdd.backend.user.SessionStorage;
import com.tdd.backend.user.data.UserSession;
import com.tdd.backend.user.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		boolean isAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		boolean isUserSession = parameter.getParameterType().equals(UserSession.class);

		return isAnnotation && isUserSession;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		Cookie[] cookies = Objects.requireNonNull(request).getCookies();
		if (cookies.length == 0) {
			throw new UnauthorizedException();
		}

		String accessToken = cookies[0].getValue();
		if (SessionStorage.isSession(accessToken)) {
			return SessionStorage.getSession(accessToken);
		}
		throw new UnauthorizedException();
	}
}
