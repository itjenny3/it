package com.itjenny.domain;

public enum ProviderType {
	facebook, twitter, google, it;

	public static final String COLUMN_DEFINITION = "enum ('facebook', 'twitter', 'google', 'it')";
}
