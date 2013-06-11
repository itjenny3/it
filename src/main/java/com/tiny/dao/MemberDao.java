package com.tiny.dao;

import java.util.List;
import java.util.Map;

import com.tiny.model.Member;

public interface MemberDao {
	public void save(Member member);
	public List<Member> getAll();
	public Member get(String providerUserId);
	public Member getByProviderUserId(String providerUserId);
	public String getName(String providerUserId);
	public Integer getChanceToDoc(String providerUserId);
	public Integer getChanceToComment(String providerUserId);
	public Integer getChanceToLike(String providerUserId);
	public Integer getChanceToDislike(String providerUserId);
	public Integer count();
	public void updateName(Map<String, Object> map);
	public void increaseDoc(String providerUserId);
	public void decreaseDoc(String providerUserId);
	public void increaseComment(String providerUsreId);
	public void decreaseComment(String providerUserId);
	public void increaseCommentOnMyDoc(String providerUserId);
	public void decreaseCommentOnMyDoc(String providerUserId);
	public void decreaseCommentOnMyDocByPoint(Map<String, Object> map);
	public void increaseLike(String providerUserId);
	public void decreaseLike(String providerUserId);
	public void increaseLikeOnMyDoc(String providerUserId);
	public void decreaseLikeOnMyDoc(String providerUserId);
	public void decreaseLikeOnMyDocByPoint(Map<String, Object> map);
	public void increaseDislike(String providerUserId);
	public void decreaseDislike(String providerUserId);
	public void increaseDislikeOnMyDoc(String providerUserId);
	public void decreaseDislikeOnMyDoc(String providerUserId);
	public void decreaseDislikeOnMyDocByPoint(Map<String, Object> map);
	public void increaseSharing(String providerUserId);
	public void decreaseSharing(String providerUserId);
	public void increaseSharingOfMyDoc(String providerUserId);
	public void decreaseSharingOfMyDoc(String providerUserId);
	public void increaseChanceToDoc(String providerUserId);
	public void decreaseChanceToDoc(String providerUserId);
	public void increaseChanceToComment(String providerUserId);
	public void decreaseChanceToComment(String providerUserId);
	public void increaseChanceToLike(String providerUserId);
	public void decreaseChanceToLike(String providerUserId);
	public void increaseChanceToDislike(String providerUserId);
	public void decreaseChanceToDislike(String providerUserId);
	public void updateLastLoginDate(String providerUserId);
	public void delete(String userId);
}