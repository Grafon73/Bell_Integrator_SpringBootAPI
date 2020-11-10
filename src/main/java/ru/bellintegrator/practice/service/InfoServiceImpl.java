package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.infodao.InfoDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.view.info.CountryView;
import ru.bellintegrator.practice.view.info.DocView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class InfoServiceImpl implements InfoService{


    private final InfoDao dao;
    private final MapperFactory mapperFactory;


    @Autowired
    public InfoServiceImpl(InfoDao dao,MapperFactory mapperFactory) {
        this.mapperFactory=mapperFactory;
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<DocView> allDocs() {
        List<Doc> docs = dao.allDocs();
        return docs.stream()
                .map(mapperFactory.getMapperFacade(Doc.class, DocView.class)::map)
                .collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<CountryView> allCountries() {
        List<Country> types = dao.allCountries();
        return types.stream()
                .map(mapperFactory.getMapperFacade(Country.class, CountryView.class)::map)
                .collect(Collectors.toList());
    }
}
