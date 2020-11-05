package ru.bellintegrator.practice.dao.infodao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
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
        CriteriaQuery<Doc> criteria = em.getCriteriaBuilder().createQuery(Doc.class);
        criteria.from(Doc.class);
        return em.createQuery(criteria).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> allCountries() {
        CriteriaQuery<Country> criteria = em.getCriteriaBuilder().createQuery(Country.class);
        criteria.from(Country.class);
        return em.createQuery(criteria).getResultList();
    }
}
