package ru.bellintegrator.practice.dao.orgdao;

import ru.bellintegrator.practice.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */

public interface OrgDao {
    /**
     * Получить все объекты Organization
     *
     * @return
     */
    List<Organization> all();

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(int id);

    /**
     * Получить Organization по имени
     *
     * @param name
     * @return
     */
    List<Organization> loadByName(Organization organization);

    /**
     * Сохранить Organization
     *
     * @param org
     */
    void save(Organization org);


    /**
     * Изменить Organization
     *
     * @param organization
     */
    void edit(Organization organization);
}
