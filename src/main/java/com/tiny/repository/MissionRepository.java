package com.tiny.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.MissionDao;
import com.tiny.model.Mission;

@Repository
public class MissionRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(MissionRepository.class);

	@Autowired
	private MissionDao missionDao;

	public void save(Mission mission) {
		missionDao.save(mission);
	}

	public Mission get(Integer missionId) {
		return missionDao.get(missionId);
	}
}
