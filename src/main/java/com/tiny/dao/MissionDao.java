package com.tiny.dao;

import com.tiny.model.Mission;

public interface MissionDao {
	public void save(Mission mission);
	public Mission get(Integer missionId);
}