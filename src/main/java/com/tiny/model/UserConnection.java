package com.tiny.model;

import lombok.Data;

@Data
public class UserConnection {
	private String userId;
	private String providerId;
	private String providerUserId;
	private int rank;
	private String displayName;
	private String profileUrl;
	private String imageUrl;
	private String accessToken;
	private String secret;
	private String refreshToken;
	private long expireTime;
}