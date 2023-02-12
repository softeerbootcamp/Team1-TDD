package com.tdd.backend.auth.util;

public interface EncryptHelper {
	String encrypt(String password);

	boolean isMatch(String password, String hashed);
}
