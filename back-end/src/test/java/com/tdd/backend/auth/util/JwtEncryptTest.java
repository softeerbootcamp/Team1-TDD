package com.tdd.backend.auth.util;

import static com.tdd.backend.auth.util.JwtTokenProvider.JwtTokenStatus.*;

import java.security.Key;
import java.util.Base64;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//todo: util 테스트에 맞게 mock bean 주입받아 사용하는 방법 (무조건 SpringBootTest x)
@SpringBootTest
public class JwtEncryptTest {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Test
	@DisplayName("jwt 암호화 테스트")
	void jws_encrypt() throws Exception {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
		System.out.println("encrypt msg : " + jws);

		byte[] encoded = key.getEncoded();
		String s = Base64.getEncoder().encodeToString(encoded);
		System.out.println(s);
	}

	@Test
	@DisplayName("JwtTokenProvider 빈 테스트")
	void jwtTokenProvider_test() throws Exception {
		String jws = jwtTokenProvider.generateAccessToken(1L);

		//expected
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(jwtTokenProvider.validateToken(jws)).isEqualTo(ACCESS);
		softAssertions.assertThat(jwtTokenProvider.getUserIdFromJwt(jws)).isEqualTo(1L);
	}
}
