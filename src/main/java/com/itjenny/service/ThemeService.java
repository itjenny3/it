package com.itjenny.service;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.domain.Theme;
import com.itjenny.repository.ThemeRepository;

@Service
public class ThemeService {
    private final Logger logger = LoggerFactory.getLogger(ThemeService.class);

    private final String DEFAULT = "default";

    @Autowired
    private ThemeRepository themeRepository;

    private Theme defaultTheme = null;

    public void save(Theme theme) {
	themeRepository.save(theme);
    }

    public Theme getDefault() {
	return get(DEFAULT);
    }

    public Theme get(String title) {
	Theme theme;
	theme = (Theme) ObjectUtils.defaultIfNull(
		themeRepository.findOne(title),
		themeRepository.findOne(DEFAULT));

	return theme;
    }
}