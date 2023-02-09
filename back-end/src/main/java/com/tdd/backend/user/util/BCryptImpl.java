package com.tdd.backend.user.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptImpl implements EncryptHelper {
	@Override
	public String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean isMatch(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}
}
