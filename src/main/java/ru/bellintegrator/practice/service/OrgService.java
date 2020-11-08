package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrgFilterView;
import ru.bellintegrator.practice.view.OrgView;

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
    List<OrgView> allOrg();

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
    OrgView getByID(int id);

    /**
     * Изменить Organization
     *
     * @param organization
     */
    void edit(Organization organization);


    /**
     * Получить Organization по имени
     *
     * @param organization
     * @return
     */
    List<OrgFilterView> getByName(Organization organization);

}