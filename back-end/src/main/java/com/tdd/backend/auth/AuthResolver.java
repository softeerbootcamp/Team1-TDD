package com.tdd.backend.auth;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tdd.backend.user.SessionStorage;
import com.tdd.backend.user.User;
import com.tdd.backend.user.data.Session;
import com.tdd.backend.user.exception.UnauthorizedException;
//todo : 세션 기반 로그인 유지 기술 및 테스트 코드 구현
public class AuthResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		boolean isAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		boolean isUserSession = parameter.getParameterType().equals(Session.class);

		return isAnnotation && isUserSession;
	}

	@Override
	public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		String accessToken = webRequest.getParameter("AccessToken");
		if (accessToken == null || accessToken.equals("")) {
			throw new UnauthorizedException();
		}

		if (SessionStorage.isSession(accessToken)) {
			return SessionStorage.getSession(accessToken).getUser();

		}
		throw new UnauthorizedException();
	}
}
