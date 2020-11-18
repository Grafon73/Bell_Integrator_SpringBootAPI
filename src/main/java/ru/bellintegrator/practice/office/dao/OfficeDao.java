package ru.bellintegrator.practice.office.dao;


import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить все объекты Office
     */
    List<Office> all();

    /**
     * Получить Office по идентификатору
     */
    Office loadById(int id);

    /**
     * Получить Office по фильтру
     */
    List<Office> loadByName(Office office);

    /**
     * Сохранить Office
     */
    void save(Office office);


    /**
     * Изменить Office
     */
    void edit(Office office);
}
