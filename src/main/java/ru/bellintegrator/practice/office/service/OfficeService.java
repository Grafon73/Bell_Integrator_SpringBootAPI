package ru.bellintegrator.practice.office.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeFilterView;
import ru.bellintegrator.practice.office.view.OfficeSaveView;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OfficeService {

    /**
     * Получить все объекты Office
     *
     * @return List<OfficeView>
     */
    List<OfficeView> allOrg();

    /**
     * Сохранить Office
     *
     * @param office
     */
    void add(OfficeSaveView office);

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return OfficeView
     */
    OfficeView getByID(int id);

    /**
     * Изменить Office
     *
     * @param office
     */
    void edit(OfficeView office);

    /**
     * Получить Office по фильтру
     *
     * @param office
     * @return List<OfficeFilterView>
     */
    List<OfficeFilterView> getByName(Office office);
}
