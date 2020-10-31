package ru.bellintegrator.practice.dao.infodao;


import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;

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
