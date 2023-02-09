package com.tdd.backend.user.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaltingEncryptTest {

	@Autowired private EncryptHelper encryptHelper;

	@Test
	@DisplayName("비밀번호 검증 (BCrypt")
	void bcrypt_pwd() {
		String password = "육식주의자";

		String encrypted = encryptHelper.encrypt(password);

		Assertions.assertThat(encryptHelper.isMatch(password, encrypted)).isTrue();
	}

}
