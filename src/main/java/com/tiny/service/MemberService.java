package com.tiny.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;

import com.tiny.model.Member;
import com.tiny.repository.CommentRepository;
import com.tiny.repository.DocOnMemberRepository;
import com.tiny.repository.DocumentRepository;
import com.tiny.repository.ItemOnMemberRepository;
import com.tiny.repository.LikeRepository;
import com.tiny.repository.MemberRepository;
import com.tiny.repository.MissionOnMemberRepository;
import com.tiny.social.SecurityContext;

@Service
public class MemberService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	private FacebookService facebookService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private ItemOnMemberRepository itemOnMemberRepository;

	@Autowired
	private MissionOnMemberRepository missionOnMemberRepository;
	
	@Autowired
	private DocOnMemberRepository docOnMemberRepository;
	
	@Autowired
	private SecurityContext securityContext;

	public void save() {
		FacebookProfile facebookProfile = facebookService.getProfile();
		Member member = new Member();
		member.setProviderUserId(facebookProfile.getId());
		member.setName(facebookProfile.getName());
		member.setGender(facebookProfile.getGender().equals("male"));
		member.setEmail(facebookProfile.getEmail());
		member.setLocale(facebookProfile.getLocale().getCountry());
		memberRepository.save(member);
	}

	public void setUsage(Member member) {
		member.setUsageOfDoc(documentRepository.countDocForLast1Hour(securityContext.getProviderUserId()));
		member.setUsageOfComment(commentRepository.countCommentForLast1Hour(securityContext.getProviderUserId()));
		member.setUsageOfLike(likeRepository.countLikeForLast1Hour(securityContext.getProviderUserId()));
		member.setUsageOfDislike(likeRepository.countDislikeForLast1Hour(securityContext.getProviderUserId()));
	}

	public Member get() {
		Member member = memberRepository.get(securityContext.getProviderUserId());
		if (member == null) {
			save();
			member = memberRepository.get(securityContext.getProviderUserId());
		}

		member.setPoint(member.getDoc() * 10 + member.getLike() + member.getDislike() + member.getCommentOnMyDoc()
				+ member.getLikeOnMyDoc() - member.getDislikeOnMyDoc());
		member.setItems(itemOnMemberRepository.get(securityContext.getProviderUserId()));
		member.setMissions(missionOnMemberRepository.get(securityContext.getProviderUserId()));
		member.setDocsOnMember(docOnMemberRepository.get(securityContext.getProviderUserId()));
		return member;
	}

	public Member getByProviderUserId() {
		String providerUserId = securityContext.getProviderUserId();
		Member member = memberRepository.getByProviderUserId(providerUserId);
		if (member == null) {
			save();
			member = memberRepository.getByProviderUserId(providerUserId);
		}

		return member;
	}

	public boolean isExisted() {
		boolean isExisted = true;
		Member member = memberRepository.get(securityContext.getProviderUserId());
		if (member == null) {
			isExisted = false;
		}
		return isExisted;
	}

	public boolean isChanceToDoc() {
		Integer chanceToDoc = memberRepository.getChanceToDoc(securityContext.getProviderUserId());
		Integer countDocForLast1Hour = documentRepository.countDocForLast1Hour(securityContext.getProviderUserId());
		return (chanceToDoc - countDocForLast1Hour) > 0;
	}

	public boolean isChanceToComment() {
		Integer chanceToComment = memberRepository.getChanceToComment(securityContext.getProviderUserId());
		Integer countCommentForLast1Hour = commentRepository.countCommentForLast1Hour(securityContext
				.getProviderUserId());
		return (chanceToComment - countCommentForLast1Hour) > 0;
	}

	public boolean isChanceToLike() {
		Integer chanceToLike = memberRepository.getChanceToLike(securityContext.getProviderUserId());
		Integer countLikeForLast1Hour = likeRepository.countLikeForLast1Hour(securityContext.getProviderUserId());
		return (chanceToLike - countLikeForLast1Hour) > 0;
	}

	public boolean isChanceToDislike() {
		Integer chanceToDislike = memberRepository.getChanceToLike(securityContext.getProviderUserId());
		Integer countDislikeForLast1Hour = likeRepository.countDislikeForLast1Hour(securityContext.getProviderUserId());
		return (chanceToDislike - countDislikeForLast1Hour) > 0;
	}

	public void updateName(String name) {
		memberRepository.updateName(securityContext.getProviderUserId(), name);
	}

	public void increaseChanceToDoc() {
		memberRepository.increaseChanceToDoc(securityContext.getProviderUserId());
	}

	public void decreaseChanceToDoc() {
		memberRepository.decreaseChanceToDoc(securityContext.getProviderUserId());
	}

	public void increaseChanceToComment() {
		memberRepository.increaseChanceToComment(securityContext.getProviderUserId());
	}

	public void decreaseChanceToComment() {
		memberRepository.decreaseChanceToComment(securityContext.getProviderUserId());
	}

	public void increaseChanceToLike() {
		memberRepository.increaseChanceToLike(securityContext.getProviderUserId());
	}

	public void decreaseChanceToLike() {
		memberRepository.decreaseChanceToLike(securityContext.getProviderUserId());
	}

	public void increaseChanceToDislike() {
		memberRepository.increaseChanceToDislike(securityContext.getProviderUserId());
	}

	public void decreaseChanceToDislike() {
		memberRepository.decreaseChanceToDislike(securityContext.getProviderUserId());
	}

	public void updateLastLoginTime() {
		memberRepository.updateLastLoginDate(securityContext.getProviderUserId());
	}
}