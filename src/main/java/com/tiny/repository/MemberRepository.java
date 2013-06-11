package com.tiny.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.MemberDao;
import com.tiny.model.Member;

@Repository
public class MemberRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberRepository.class);

	@Autowired
	private MemberDao memberDao;

	public void save(Member member) {
		memberDao.save(member);
	}

	public List<Member> getAll() {
		return memberDao.getAll();
	}

	public Member get(String providerUserId) {
		return memberDao.get(providerUserId);
	}

	public Member getByProviderUserId(String providerUserId) {
		return memberDao.getByProviderUserId(providerUserId);
	}

	public String getName(String providerUserId) {
		return memberDao.getName(providerUserId);
	}

	public Integer getChanceToDoc(String providerUserId) {
		return memberDao.getChanceToDoc(providerUserId);
	}

	public Integer getChanceToComment(String providerUserId) {
		return memberDao.getChanceToComment(providerUserId);
	}

	public Integer getChanceToLike(String providerUserId) {
		return memberDao.getChanceToLike(providerUserId);
	}

	public Integer getChanceToDislike(String providerUserId) {
		return memberDao.getChanceToDislike(providerUserId);
	}

	public void updateName(String providerUserId, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("providerUserId", providerUserId);
		map.put("name", name);
		memberDao.updateName(map);
	}

	public void increasePointDoc(String providerUserId) {
		memberDao.increaseDoc(providerUserId);
	}

	public void decreasePointDoc(String providerUserId) {
		memberDao.decreaseDoc(providerUserId);
	}

	public void increaseComment(String providerUserId) {
		memberDao.increaseComment(providerUserId);
	}

	public void decreaseComment(String providerUserId) {
		memberDao.decreaseComment(providerUserId);
	}

	public void increaseCommentOnMyDoc(String providerUserId) {
		memberDao.increaseCommentOnMyDoc(providerUserId);
	}

	public void decreaseCommentOnMyDoc(String providerUserId) {
		memberDao.decreaseCommentOnMyDoc(providerUserId);
	}

	public void decreaseCommentOnMyDocByPoint(String providerUserId, Integer point) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("providerUserId", providerUserId);
		map.put("point", point);
		memberDao.decreaseCommentOnMyDocByPoint(map);
	}

	public void increaseLike(String providerUserId) {
		memberDao.increaseLike(providerUserId);
	}

	public void decreaseLike(String providerUserId) {
		memberDao.decreaseLike(providerUserId);
	}

	public void increaseLikeOnMyDoc(String providerUserId) {
		memberDao.increaseLikeOnMyDoc(providerUserId);
	}

	public void decreaseLikeOnMyDoc(String providerUserId) {
		memberDao.decreaseLikeOnMyDoc(providerUserId);
	}

	public void decreaseLikeOnMyDocByPoint(String providerUserId, Integer point) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("providerUserId", providerUserId);
		map.put("point", point);
		memberDao.decreaseLikeOnMyDocByPoint(map);
	}

	public void increaseDislike(String providerUserId) {
		memberDao.increaseDislike(providerUserId);
	}

	public void decreaseDislike(String providerUserId) {
		memberDao.decreaseDislike(providerUserId);
	}

	public void increaseDislikeOnMyDoc(String providerUserId) {
		memberDao.increaseDislikeOnMyDoc(providerUserId);
	}

	public void decreaseDislikeOnMyDoc(String providerUserId) {
		memberDao.decreaseDislikeOnMyDoc(providerUserId);
	}

	public void decreaseDislikeOnMyDocByPoint(String providerUserId, Integer point) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("providerUserId", providerUserId);
		map.put("point", point);
		memberDao.decreaseDislikeOnMyDocByPoint(map);
	}

	public void increaseSharing(String providerUserId) {
		memberDao.increaseSharing(providerUserId);
	}

	public void decreaseSharing(String providerUserId) {
		memberDao.decreaseSharing(providerUserId);
	}

	public void increasePointBeShared(String providerUserId) {
		memberDao.increaseSharingOfMyDoc(providerUserId);
	}

	public void increaseChanceToDoc(String providerUserId) {
		memberDao.increaseChanceToDoc(providerUserId);
	}

	public void decreaseChanceToDoc(String providerUserId) {
		memberDao.decreaseChanceToDoc(providerUserId);
	}

	public void increaseChanceToComment(String providerUserId) {
		memberDao.increaseChanceToComment(providerUserId);
	}

	public void decreaseChanceToComment(String providerUserId) {
		memberDao.decreaseChanceToComment(providerUserId);
	}

	public void increaseChanceToLike(String providerUserId) {
		memberDao.increaseChanceToLike(providerUserId);
	}

	public void decreaseChanceToLike(String providerUserId) {
		memberDao.decreaseChanceToLike(providerUserId);
	}

	public void increaseChanceToDislike(String providerUserId) {
		memberDao.increaseChanceToDislike(providerUserId);
	}

	public void decreaseChanceToDislike(String providerUserId) {
		memberDao.decreaseChanceToDislike(providerUserId);
	}

	public void updateLastLoginDate(String providerUserId) {
		memberDao.updateLastLoginDate(providerUserId);
	}

	public void delete(String providerUserId) {
		memberDao.delete(providerUserId);
	}
}
