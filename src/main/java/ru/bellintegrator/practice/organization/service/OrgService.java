package ru.bellintegrator.practice.organization.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrgFilterOutView;
import ru.bellintegrator.practice.organization.view.OrgSaveView;
import ru.bellintegrator.practice.organization.view.OrgUpdateView;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrgService {

    /**
     * Получить все объекты Organization
     *
     * @return List<OrgUpdateView>
     */
    List<OrgUpdateView> allOrg();

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    void add(OrgSaveView organization);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return OrgUpdateView
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
     * @return List<OrgFilterOutView>
     */
    List<OrgFilterOutView> getByName(Organization organization);

}