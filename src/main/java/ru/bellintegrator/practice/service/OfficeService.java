package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.Office;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OfficeService {

    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> allOrg();

    /**
     * Сохранить Office
     *
     * @param office
     */
    void add(Office office);

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office  getByID(int id);

    /**
     * Изменить Office
     *
     * @param office
     */
    void edit(Office office);
}
