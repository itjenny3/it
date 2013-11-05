package com.itjenny.service;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.domain.Setting;
import com.itjenny.repository.SettingRepository;

@Service
public class SettingService {
    private final Logger logger = LoggerFactory.getLogger(SettingService.class);

    @Autowired
    private SettingRepository settingRepository;

    private Setting defaultSetting = new Setting();

    public void save(Setting setting) {
	settingRepository.save(setting);
    }

    public List<Setting> getAll() {
	return settingRepository.findAll();
    }

    public Setting get(String userId) {
	Setting setting;
	if (userId == null) {
	    return defaultSetting;
	}
	setting = (Setting) ObjectUtils.defaultIfNull(
		settingRepository.findOne(userId), defaultSetting);

	return setting;
    }
}