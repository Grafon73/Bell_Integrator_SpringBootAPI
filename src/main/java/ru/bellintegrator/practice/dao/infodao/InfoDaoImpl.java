package ru.bellintegrator.practice.dao.infodao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class InfoDaoImpl implements InfoDao{

    private final EntityManager em;

    @Autowired
    public InfoDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Doc> allDocs() {
        TypedQuery<Doc> query = em.createQuery("SELECT o FROM Doc o", Doc.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> allCountries() {
        TypedQuery<Country> query = em.createQuery("SELECT o FROM Country o", Country.class);
        return query.getResultList();
    }
}
