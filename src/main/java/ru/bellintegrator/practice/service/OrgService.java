package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrgFilterOutView;
import ru.bellintegrator.practice.view.organization.OrgSaveView;
import ru.bellintegrator.practice.view.organization.OrgUpdateView;

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
    List<OrgUpdateView> allOrg();

    /**
     * Сохранить Organization
     *
     * @param org
     * @param organization
     */
    void add(OrgSaveView organization);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    OrgUpdateView getByID(int id);

    /**
     * Изменить Organization
     *
     * @param organization
     */
    void edit(OrgUpdateView organization);


    /**
     * Получить Organization по имени
     *
     * @param organization
     * @return
     */
    List<OrgFilterOutView> getByName(Organization organization);

}