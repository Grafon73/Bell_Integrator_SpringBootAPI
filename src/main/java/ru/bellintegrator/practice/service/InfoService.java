package ru.bellintegrator.practice.service;


import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.Office;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface InfoService {

    /**
     * Получить все объекты Doc
     *
     * @return
     */
    List<Doc> allDocs();

    /**
     * Получить все объекты Country
     *
     * @return
     */
    List<Country> allCountries();
}
