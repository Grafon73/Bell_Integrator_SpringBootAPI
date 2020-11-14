package ru.bellintegrator.practice.document.dao;


import ru.bellintegrator.practice.document.model.Country;
import ru.bellintegrator.practice.document.model.Doc;

import java.util.List;

/**
 * DAO для работы с Info
 */
public interface InfoDao {

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
