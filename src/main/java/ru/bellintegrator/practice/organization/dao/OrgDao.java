package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */

public interface OrgDao {
    /**
     * Получить все объекты Organization
     *
     * @return List<Organization>
     */
    List<Organization> all();

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return Organization
     */
    Organization loadById(int id);

    /**
     * Получить Organization по имени
     *
     * @param organization
     * @return List<Organization>
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
