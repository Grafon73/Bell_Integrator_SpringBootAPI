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
     */
    List<Doc> allDocs();

    /**
     * Получить все объекты Country
     */
    List<Country> allCountries();

    /**
     * Получить Country по Code
     */
    Country loadCountryByCode(Integer code);

    /**
     * Получить Doc по code
     */
    Doc loadDocByCode(Integer code);

}
