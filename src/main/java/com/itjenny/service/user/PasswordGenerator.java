package com.itjenny.service.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {
	private static final int DEFAULT_RANDOM_PASSWORD_LENGTH = 12;

	public String generate() {
		return RandomStringUtils.randomAlphanumeric(DEFAULT_RANDOM_PASSWORD_LENGTH);
	}
}
