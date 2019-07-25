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
import javax.mail.internet.InternetAddress;
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

        log.info("Sending Email to: " + mailModel.getTo().toString());

        /** Store Data in HashMap **/
        Map model = new HashMap();
        model.put("name",mailModel.getName());
        model.put("content",mailModel.getContent());
        model.put("department","NOC Department");
        mailModel.setModel(model);


        /** MIME Message **/
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                                                                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                                                    StandardCharsets.UTF_8.name());
        mimeMessageHelper.addInline("logo",new ClassPathResource("classpath:/templates/mytel_img.png"));
        Template template = emailConfig.getTemplate("email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());

        mimeMessageHelper.setFrom("arkarmoe1@mytel.com.mm");
       // mimeMessageHelper.setTo(mailModel.getTo()); //single recipient
        //mimeMessageHelper.setTo(InternetAddress.parse(mailModel.getTo())); //multiple receipients
       // mimeMessageHelper.setTo(new String[]{"arkarmoe1@mytel.com.mm", "arkarmoe17@gmail.com"});
        mimeMessageHelper.setTo(mailModel.getTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mailModel.getSubject());



        emailSender.send(message);
    }
}
