package com.arkarmoe.email_notification.service;

import com.arkarmoe.email_notification.model.MailModel;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SendingEmailService {
    void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException;
}
