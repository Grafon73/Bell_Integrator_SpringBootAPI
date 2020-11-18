package ru.bellintegrator.practice.office.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.office.view.OfficeFilterView;
import ru.bellintegrator.practice.office.view.OfficeSaveView;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

/**
 * Сервис Office
 */
@Validated
public interface OfficeService {

    /**
     * Получить все объекты Office
     */
    List<OfficeView> allOrg();

    /**
     * Сохранить Office
     */
    void add(OfficeSaveView office);

    /**
     * Получить Office по идентификатору
     */
    OfficeView getByID(int id);

    /**
     * Изменить Office
     */
    void edit(OfficeView office);

    /**
     * Получить Office по фильтру
     */
    List<OfficeFilterView> getByName(OfficeSaveView office);
}
