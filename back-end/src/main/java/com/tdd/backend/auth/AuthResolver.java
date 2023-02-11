package com.tdd.backend.auth;

import static com.tdd.backend.auth.JwtTokenProvider.JwtTokenStatus.*;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tdd.backend.auth.JwtTokenProvider.JwtTokenStatus;
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

		JwtTokenStatus jwtTokenStatus = jwtTokenProvider.validateToken(jws);

		//todo : 여기서 분기하는 부분 refactoring 필요
		if (jwtTokenStatus.equals(ACCESS)) {
			String emailFormJwt = jwtTokenProvider.getEmailFormJwt(jws);
			return UserToken.builder()
				.email(emailFormJwt)
				.build();
		} else if (jwtTokenStatus.equals(EXPIRED)) {
			//todo : code to redirect to POST "/reissue"
			log.info(">> jwt token expired ! please redirect to POST /reissue to regenerate new access token");
			return UserToken.builder().email("expired email").build();
			// RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
			// redirectAttributes.addFlashAttribute("jws", jws);
			// return new ModelAndView("redirect:/reissue", redirectAttributes.asMap());
		}
		throw new UnauthorizedException();

	}
}
