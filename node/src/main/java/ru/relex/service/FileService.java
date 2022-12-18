package ru.relex.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.relex.entity.AppDocument;
import ru.relex.entity.AppPhoto;
import ru.relex.service.enums.LinkType;

public interface FileService {
    AppDocument processDoc(Message telegramMessage);
    AppPhoto processPhoto(Message telegramMessage);
    String generateLink(Long docId, LinkType linkType);
}
