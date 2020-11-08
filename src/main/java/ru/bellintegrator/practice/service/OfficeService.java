package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.OfficeFilterView;
import ru.bellintegrator.practice.view.OfficeView;

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
    void add(Office office);

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
    void edit(Office office);

    /**
     * Получить Office по фильтру
     *
     * @param office
     * @return
     */
    List<OfficeFilterView> getByName(Office office);
}
