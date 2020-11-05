package ru.bellintegrator.practice.service;


import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.CountryView;
import ru.bellintegrator.practice.view.DocView;

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
    List<DocView> allDocs();

    /**
     * Получить все объекты Country
     *
     * @return
     */
    List<CountryView> allCountries();
}
