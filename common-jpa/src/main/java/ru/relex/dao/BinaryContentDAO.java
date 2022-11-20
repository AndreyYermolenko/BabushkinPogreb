package ru.relex.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.entity.BinaryContent;

public interface BinaryContentDAO extends JpaRepository<BinaryContent, Long> {
}
