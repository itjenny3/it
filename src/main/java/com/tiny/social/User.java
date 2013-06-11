package com.tiny.social;

public class User {
	private String id;
	// added by template
	private String providerUserId;

	public User(String id) {
		this.id = id;
	}
	
	public User(String id, String providerUserId) {
		this.id = id;
		this.providerUserId = providerUserId;
	}

	public String getId() {
		return id;
	}
	
	public String getProviderUserId() {
		return this.providerUserId;
	}
}
