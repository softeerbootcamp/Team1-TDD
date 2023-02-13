package com.tdd.backend.auth.data;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class RefreshToken implements Serializable {

	private Long userId;
	private String refreshToken;
}
