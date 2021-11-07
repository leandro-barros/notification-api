package com.softmei.notification.consumer;

import com.softmei.notification.dto.request.EmailRequestDto;
import com.softmei.notification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listenerEmail(@Payload EmailRequestDto emailRequestDto) {
        emailService.sendEmail(emailRequestDto);
    }
}
