package ru.bellintegrator.practice.office.dao;


import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить все объекты Office
     *
     * @return List<Office>
     */
    List<Office> all();

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return Office
     */
    Office loadById(int id);

    /**
     * Получить Office по фильтру
     *
     * @param office
     * @return List<Office>
     */
    List<Office> loadByName(Office office);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);


    /**
     * Изменить Office
     *
     * @param office
     */
    void edit(Office office);
}
