package com.tiny.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiny.common.CommonTest;
import com.tiny.model.Member;
import com.tiny.model.UserConnection;

public class MemberDaoTest extends CommonTest {
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private UserConnectionDao userConnectionDao;

	private final String userId = "userId";
	private final String providerUserId = "providerUserId";
	private final String facebook = "facebook";
	private final String accessToken = "accessToken";

	@Before
	public void setUp() {
		UserConnection userConnection = new UserConnection();
		userConnection.setUserId(userId);
		userConnection.setProviderId(facebook);
		userConnection.setProviderUserId(providerUserId);
		userConnection.setRank(1);
		userConnection.setAccessToken(accessToken);
		userConnectionDao.save(userConnection);
	}
	
	@Test
	public void save() {
		// Given
		Integer count = memberDao.count();

		// When
		memberDao.save(createMember());

		// Then
		assertThat(memberDao.count(), is(count + 1));
	}

	@Test
	public void get() {
		// Given
		Member memberTmp = createMember();
		memberDao.save(memberTmp);

		// When
		Member member = memberDao.getByProviderUserId(memberTmp.getProviderUserId());

		// Then
		assertThat(member.getProviderUserId(), is(memberTmp.getProviderUserId()));
	}

	@Test
	public void updateLastLoginDate() throws InterruptedException {
		// Given
		Member member = createMember();
		memberDao.save(member);

		// When
		Thread.sleep(1000);
		memberDao.updateLastLoginDate(providerUserId);
		Member memberAfterUpdate = memberDao.getByProviderUserId(member.getProviderUserId());

		// Then
		assertThat(member.getLastLoginDate().toString(), not(memberAfterUpdate.getLastLoginDate().toString()));
	}

	@Test
	public void delete() {
		// Given
		Member member = createMember();
		memberDao.save(member);
		Integer count = memberDao.count();

		// When
		memberDao.delete(member.getProviderUserId());

		// Then
		assertThat(memberDao.count(), is(count - 1));
	}

	private Member createMember() {
		Member member = new Member();
		member.setProviderUserId(providerUserId);
		member.setRegDate(new Date());
		member.setLastLoginDate(new Date());
		return member;
	}
}