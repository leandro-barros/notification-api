package com.softmei.notification.service;

import com.softmei.notification.dto.request.EmailRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Locale;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine thymeleaf;

    public void sendEmail(String remetente,
                           List<String> destinatarios, String assunto, String mensagem) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(remetente);
            message.setTo(destinatarios.toArray(new String[destinatarios.size()]));
            message.setSubject(assunto);
            message.setText(mensagem);

            mailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Problemas com o envio de e-mail!", e);
        }
    }

    public void sendEmail(EmailRequestDto emailRequestDto) {
        Context context = new Context(new Locale("pt", "BR"));
        emailRequestDto.getVariables().entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));
        String message = thymeleaf.process(emailRequestDto.getText(), context);
        this.sendEmail(emailRequestDto.getEmailFrom(), emailRequestDto.getEmailsTo(), emailRequestDto.getSubject(), message);
    }

}
