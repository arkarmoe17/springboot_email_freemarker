package com.arkarmoe.email_notification.controller;

import com.arkarmoe.email_notification.model.MailModel;
import com.arkarmoe.email_notification.service.SendingEmailService;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by Arkar Moe
 * 24-July-2019
 * **/
@CrossOrigin(
        allowCredentials = "true",
        origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@RestController
@RequestMapping("/email_noti")
public class EmailController {
    private Logger logger = LoggerFactory.getLogger(EmailController.class);


    @Autowired
    private SendingEmailService sendingEmailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmailRequest(@RequestBody MailModel mailModel){
        try {
            sendingEmailService.sendEmail(mailModel);
            return ResponseEntity.ok().body(mailModel.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        } catch (TemplateException e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        }
    }



}
