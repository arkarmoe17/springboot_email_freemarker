package com.arkarmoe.email_notification.service.impl;

import com.arkarmoe.email_notification.model.MailModel;
import com.arkarmoe.email_notification.service.SendingEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendingEmailServiceImpl implements SendingEmailService {
    private static Logger log = LoggerFactory.getLogger(SendingEmailServiceImpl.class);

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    @Override
    public void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException {

        Map model = new HashMap();
        model.put("name",mailModel.getName());
        model.put("content",mailModel.getContent());
        model.put("department","NOC Department");

        mailModel.setModel(model);

        log.info("Sending Email to: " + mailModel.getTo());


        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                                                                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                                                    StandardCharsets.UTF_8.name());
        mimeMessageHelper.addInline("logo",new ClassPathResource("classpath:/mytel_img.png"));
        Template template = emailConfig.getTemplate("email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());

        mimeMessageHelper.setTo(mailModel.getTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mailModel.getSubject());
        mimeMessageHelper.setFrom(mailModel.getFrom());

        emailSender.send(message);
    }
}