package ru.relex.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.entity.AppDocument;

public interface AppDocumentDAO extends JpaRepository<AppDocument, Long> {
}
