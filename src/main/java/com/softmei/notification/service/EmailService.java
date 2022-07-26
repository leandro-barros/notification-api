package com.softmei.notification.service;

import com.softmei.notification.dto.request.EmailRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine thymeleaf;

    public void sendEmail(String emailFrom,
                           List<String> emailsTo, String subject, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailFrom);
            helper.setTo(emailsTo.toArray(new String[emailsTo.size()]));
            helper.setSubject(subject);
            helper.setText(message, true);
            LOG.info("Enviando e-mail");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOG.info("Erro no envio de e-mail", e);
            throw new RuntimeException("Erro no envio de e-mail!", e);
        }
    }

    public void sendEmail(EmailRequestDto emailRequestDto) {
        LOG.info("Criando template do e-mail");
        Context context = new Context(new Locale("pt", "BR"));
        emailRequestDto.getVariables().entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));
        String message = thymeleaf.process(emailRequestDto.getText(), context);
        this.sendEmail(emailRequestDto.getEmailFrom(), emailRequestDto.getEmailsTo(), emailRequestDto.getSubject(), message);
    }

}
