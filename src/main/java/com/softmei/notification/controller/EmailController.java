package com.softmei.notification.controller;

import com.softmei.notification.dto.request.EmailRequestDto;
import com.softmei.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/email")
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("send")
    public void sendEmail(@RequestBody EmailRequestDto emailRequestDto) {
        emailService.sendEmail(emailRequestDto);
    }

}
