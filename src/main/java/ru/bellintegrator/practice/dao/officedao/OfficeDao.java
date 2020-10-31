package ru.bellintegrator.practice.dao.officedao;


import ru.bellintegrator.practice.model.Office;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> all();

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(int id);

    /**
     * Получить Office по имени
     *
     * @param name
     * @return
     */
    Office loadByName(String name);

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
