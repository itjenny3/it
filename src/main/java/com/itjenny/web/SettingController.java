package com.itjenny.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itjenny.domain.Setting;
import com.itjenny.service.SettingService;
import com.itjenny.support.Const;
import com.itjenny.support.URL;
import com.itjenny.support.security.SessionService;

@Controller
@RequestMapping(URL.SETTING)
public class SettingController {
    private final Logger logger = LoggerFactory
	    .getLogger(SettingController.class);

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SettingService settingService;

    @RequestMapping(value = "{option}/{value}")
    public @ResponseBody
    void save(@PathVariable String option, @PathVariable int value) {
	if (sessionService.getLoginUser().getUserId() == null) {
	    return;
	}

	Setting setting = settingService.get(sessionService.getLoginUser()
		.getUserId());
	setting.setUserId(sessionService.getLoginUser().getUserId());

	// TODO : change to enum to use switch.
	if (option.equals(Const.PAGINATION)) {
	    setting.setPagination(value == 1);
	} else if (option.equals(Const.ONELINE)) {
	    setting.setOneline(value == 1);
	} else if (option.equals(Const.FONTSIZE)) {
	    setting.setFontsize(value);
	}
	settingService.save(setting);
	return;
    }
}