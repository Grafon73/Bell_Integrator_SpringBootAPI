package ru.bellintegrator.practice.document.service;


import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.document.view.CountryView;
import ru.bellintegrator.practice.document.view.DocView;

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
