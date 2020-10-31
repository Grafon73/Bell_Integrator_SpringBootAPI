package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.Organization;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrgService {

    /**
     * Получить все объекты Organization
     *
     * @return
     */
    List<Organization> allOrg();

    /**
     * Сохранить Organization
     *
     * @param org
     */
    void add(Organization organization);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization  getByID(int id);

    /**
     * Изменить Organization
     *
     * @param organization
     */
    void edit(Organization organization);
}