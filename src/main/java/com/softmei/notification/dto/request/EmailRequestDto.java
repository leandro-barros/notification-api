package com.softmei.notification.dto.request;

import lombok.Data;

@Data
public class EmailRequestDto {

    private String emailTo;

    private String subject;

    private String text;
}
