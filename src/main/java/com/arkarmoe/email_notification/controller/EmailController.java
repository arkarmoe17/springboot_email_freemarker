package com.arkarmoe.email_notification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Arkar Moe
 * 24-July-2019
 * **/
@RestController
@RequestMapping("/email_noti")
public class EmailController {
    private Logger logger = LoggerFactory.getLogger(EmailController.class);


    @CrossOrigin
    @GetMapping("/send")
    public String testing(){
        logger.info("Testing.");
        return "Hello";
    }
}
