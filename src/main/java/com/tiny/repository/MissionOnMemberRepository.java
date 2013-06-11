package com.tiny.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.MissionDao;
import com.tiny.dao.MissionOnMemberDao;
import com.tiny.model.Mission;
import com.tiny.model.MissionOnMember;

@Repository
public class MissionOnMemberRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(MissionOnMemberRepository.class);

	@Autowired
	private MissionOnMemberDao missionOnMemberDao;

	@Autowired
	private MissionDao missionDao;

	public void save(MissionOnMember missionOnMember) {
		missionOnMemberDao.save(missionOnMember);
	}

	public List<Mission> get(String providerUserId) {
		List<Mission> items = new ArrayList<Mission>();
		List<MissionOnMember> missionsOnMember = missionOnMemberDao.get(providerUserId);
		for (MissionOnMember missionOnMember : missionsOnMember) {
			items.add(missionDao.get(missionOnMember.getMissionId()));
		}
		return items;
	}
}
