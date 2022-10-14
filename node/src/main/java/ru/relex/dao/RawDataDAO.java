package ru.relex.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.entity.RawData;

public interface RawDataDAO extends JpaRepository<RawData, Long> {
}
