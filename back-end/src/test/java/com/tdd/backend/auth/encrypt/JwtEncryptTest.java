package com.tdd.backend.auth.encrypt;


import java.security.Key;
import java.util.Base64;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tdd.backend.auth.jwt.JwtProvider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
public class JwtEncryptTest {

	@Autowired
	JwtProvider jwtProvider;

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
		Long id = 1L;
		String email = "test@test.com";

		String jws = jwtProvider.generateAccessToken(1L, email);

		//expected
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(jwtProvider.isValidateToken(jws)).isTrue();
		softAssertions.assertThat(jwtProvider.getUserIdFromJwt(jws)).isEqualTo(id);
		softAssertions.assertThat(jwtProvider.getEmailFromJwt(jws)).isEqualTo(email);
		softAssertions.assertAll();
	}
}
