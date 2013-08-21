package com.itjenny.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.itjenny.domain.user.SocialUser;

@Service
public class MailService {
    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Resource(name = "mailSender")
    private JavaMailSender mailSender;

    @Async
    public void sendPasswordInformation(final SocialUser user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws MessagingException {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                mimeMessage.setFrom(new InternetAddress("jenny@itjenny.com"));
                mimeMessage.setSubject("welcome");

                String fileName = "passwordinfo.ftl";
                Map<String, Object> params = Maps.newHashMap();
                params.put("user", user);
                mimeMessage.setText(createMailTemplate(fileName, params), "utf-8", "html");
            }
        };
        mailSender.send(preparator);
    }

    private String createMailTemplate(String fileName, Map<String, Object> params) {
    	return "String";
//        Template template;
//        try {
//            template = configuration.getTemplate(fileName);
//        } catch (IOException e) {
//            log.error(e.toString());
//            return null;
//        }
//
//        try {
//            String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
//            log.debug("mail auth body : {}", result);
//            return result;
//        } catch (Exception e) {
//            log.error(e.toString());
//            return null;
//        }
    }
}
