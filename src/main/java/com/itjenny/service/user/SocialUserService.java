package com.itjenny.service.user;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.itjenny.domain.ProviderType;
import com.itjenny.domain.user.ExistedUserException;
import com.itjenny.domain.user.PasswordDto;
import com.itjenny.domain.user.SocialUser;
import com.itjenny.repository.user.SocialUserRepository;
import com.itjenny.service.MailService;
import com.itjenny.support.utils.MD5Util;

@Service
@Transactional
public class SocialUserService {
    private final Logger logger = LoggerFactory
	    .getLogger(SocialUserService.class);

    @Resource(name = "usersConnectionRepository")
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private MailService mailService;

    public void createNewSocialUser(String userId, Connection<?> connection)
	    throws ExistedUserException {
	Assert.notNull(userId, "userId can't be null!");
	Assert.notNull(connection, "connection can't be null!");

	ConnectionRepository connectionRepository = usersConnectionRepository
		.createConnectionRepository(userId);
	if (!isUserIdAvailable(userId)) {
	    throw new ExistedUserException(userId + " already is existed user!");
	}

	connectionRepository.addConnection(connection);
    }

    private boolean isUserIdAvailable(String userId) {
	List<SocialUser> socialUsers = socialUserRepository
		.findsByUserId(userId);
	return socialUsers.isEmpty();
    }

    public SocialUser findById(Long id) {
	Assert.notNull(id, "id can't be null!");
	return socialUserRepository.findOne(id);
    }

    public SocialUser findByUserId(String userId) {
	Assert.notNull(userId, "userId can't be null!");

	List<SocialUser> socialUsers = socialUserRepository
		.findsByUserId(userId);
	if (socialUsers.isEmpty()) {
	    return null;
	}

	return socialUsers.get(0);
    }

    public SocialUser findByEmail(String email) {
	Assert.notNull(email, "email can't be null!");
	return socialUserRepository.findByEmail(email);
    }

    public SocialUser findByUserIdAndConnectionKey(String userId,
	    ConnectionKey connectionKey) {
	Assert.notNull(userId, "userId can't be null!");
	Assert.notNull(connectionKey, "connectionKey can't be null!");

	SocialUser socialUser = socialUserRepository
		.findByUserIdAndProviderIdAndProviderUserId(userId,
			connectionKey.getProviderId(),
			connectionKey.getProviderUserId());
	return socialUser;
    }

    public SocialUser createItUser(String userId, String email) {
	SocialUser existedUser = findByUserId(userId);
	Assert.isNull(existedUser, userId + " userId already is existed User.");

	existedUser = findByEmail(email);
	Assert.isNull(existedUser, email + " userId already is existed User.");

	String rawPassword = passwordGenerator.generate();
	logger.info("rawPassword : " + rawPassword);
	String uuid = UUID.randomUUID().toString();
	SocialUser socialUser = new SocialUser();
	socialUser.setUserId(userId);
	socialUser.setEmail(email);
	socialUser.setImageUrl(SocialUser.DEFAULT_IT_USER_PROFILE_SUFFIX
		+ MD5Util.md5Hex(email));
	socialUser.setProviderId(ProviderType.it.name());
	socialUser.setProviderUserId(userId);
	socialUser.setRank(1);
	socialUser.setAccessToken(uuid);
	socialUser.setRawPassword(rawPassword);
	socialUser.setPassword(encodePassword(rawPassword));
	socialUserRepository.save(socialUser);
	mailService.sendPasswordInformation(socialUser);
	return socialUser;
    }

    public void updateItUser(SocialUser loginUser, String email, String userId) {
	Assert.notNull(loginUser, "loginUser can't be null!");
	Assert.notNull(email, "email can't be null!");
	Assert.notNull(userId, "userId can't be null!");

	SocialUser socialUser = socialUserRepository.findOne(loginUser.getId());
	socialUser.update(email, userId);
    }

    private String encodePassword(String rawPass) {
	return passwordEncoder.encodePassword(rawPass, null);
    }

    public SocialUser changePassword(SocialUser loginUser, PasswordDto password) {
	SocialUser user = socialUserRepository.findOne(password.getId());
	if (user == null) {
	    return null;
	}
	user.changePassword(passwordEncoder, password.getOldPassword(),
		password.getNewPassword());
	return user;
    }
}