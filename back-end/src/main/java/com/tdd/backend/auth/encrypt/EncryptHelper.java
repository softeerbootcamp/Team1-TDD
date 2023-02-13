package com.tdd.backend.auth.encrypt;

public interface EncryptHelper {
	String encrypt(String password);

	boolean isMatch(String password, String hashed);
}
