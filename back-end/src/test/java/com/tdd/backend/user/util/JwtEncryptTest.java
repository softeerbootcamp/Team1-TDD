package com.tdd.backend.user.util;

import java.security.Key;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtEncryptTest {

	@Test
	@DisplayName("jwt 암호화 테스트")
	void jws_encrypt() throws Exception {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
		System.out.println("encrypt msg : " + jws);
	}
}
