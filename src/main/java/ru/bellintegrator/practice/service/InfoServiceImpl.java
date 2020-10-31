package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.infodao.InfoDao;
import ru.bellintegrator.practice.dao.officedao.OfficeDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class InfoServiceImpl implements InfoService{

    private final InfoDao dao;

    @Autowired
    public InfoServiceImpl(InfoDao dao) {
        this.dao = dao;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Doc> allDocs() {
        return dao.allDocs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> allCountries() {
        return dao.allCountries();
    }
}
