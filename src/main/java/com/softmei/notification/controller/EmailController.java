package com.softmei.notification.controller;

import com.softmei.notification.dto.request.EmailRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/email")
@RestController
public class EmailController {

    @PostMapping("send-email")
    public void sendEmail(@RequestBody EmailRequestDto emailRequestDto) {

    }

}
