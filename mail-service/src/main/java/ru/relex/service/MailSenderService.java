package ru.relex.service;

import ru.relex.dto.MailParams;

public interface MailSenderService {
    void send(MailParams mailParams);
}
