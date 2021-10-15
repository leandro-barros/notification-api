package com.softmei.notification.controller;

import com.softmei.notification.dto.request.EmailRequestDto;
import com.softmei.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/email")
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("send")
    public void sendEmail(@RequestBody EmailRequestDto emailRequestDto) {
        emailService.sendEmail(emailRequestDto);
    }

//    {
//        "emailFrom": "contatosoftmei@gmail.com",
//            "emailsTo" : [
//        "leobarros.evangelista@gmail.com"
//    ],
//        "subject": "Teste Email",
//            "text": "mail/boas-vindas",
//            "variables": {
//        "proprietario": "Leandro"
//    }
//    }

}
