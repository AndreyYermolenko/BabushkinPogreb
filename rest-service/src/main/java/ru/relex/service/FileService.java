package ru.relex.service;

import ru.relex.entity.AppDocument;
import ru.relex.entity.AppPhoto;

public interface FileService {
    AppDocument getDocument(String id);
    AppPhoto getPhoto(String id);
}
