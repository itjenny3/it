package com.itjenny.social.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.itjenny.domain.ProviderType;

public class ItUser extends User {
    private static final long serialVersionUID = 7453852286375201264L;
    private ProviderType providerType;

    public ItUser(String username, String password, ProviderType providerType,
	    Collection<? extends GrantedAuthority> authorities) {
	super(username, password, authorities);
	this.providerType = providerType;
    }

    public ProviderType getProviderType() {
	return providerType;
    }
}
