package ru.relex.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.relex.entity.AppDocument;

public interface FileService {
    AppDocument processDoc(Message externalMessage);
}
