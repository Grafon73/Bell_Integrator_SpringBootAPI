package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeFilterView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeView;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OfficeService {

    /**
     * Получить все объекты Office
     *
     * @return
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
     * @return
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
     * @return
     */
    List<OfficeFilterView> getByName(Office office);
}
