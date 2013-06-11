package com.tiny.social.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.tiny.social.SecurityContext;
import com.tiny.social.SimpleConnectionSignUp;
import com.tiny.social.User;

@Configuration
@PropertySource("classpath:social.properties")
public class SocialConfig {

	@Inject
	private Environment environment;

	@Inject
	private DataSource dataSource;
	
	@Inject
	private SecurityContext securityContext;

	/**
	 * When a new provider is added to the app, register its {@link ConnectionFactory} here.
	 * 
	 * @see FacebookConnectionFactory
	 */
	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(environment.getProperty("facebook.clientId"),
				environment.getProperty("facebook.clientSecret")));
		return registry;
	}

	/**
	 * Singleton data access object providing access to connections across all users.
	 */
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator(), Encryptors.noOpText());
		repository.setConnectionSignUp(new SimpleConnectionSignUp());
		return repository;
	}

	/**
	 * Request-scoped data access object providing access to the current user's connections.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		User user = securityContext.getCurrentUser();
		return usersConnectionRepository().createConnectionRepository(user.getId());
	}

	/**
	 * A proxy to a request-scoped object representing the current user's primary Facebook account.
	 * 
	 * @throws NotConnectedException
	 *             if the user is not connected to facebook.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook() {
		return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}
}