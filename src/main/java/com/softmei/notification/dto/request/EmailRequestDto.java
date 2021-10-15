package com.softmei.notification.dto.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EmailRequestDto {

    private String emailFrom;

    private List<String> emailsTo;

    private String subject;

    private String text;

    private Map<String, Object> variables;
}
