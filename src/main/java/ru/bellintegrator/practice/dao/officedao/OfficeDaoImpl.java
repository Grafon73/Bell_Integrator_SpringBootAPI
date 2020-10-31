package ru.bellintegrator.practice.dao.officedao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao{

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> all() {
        TypedQuery<Office> query = em.createQuery("SELECT o FROM Office o", Office.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(int id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadByName(String name) {
        CriteriaQuery<Office> criteria = buildCriteria(name);
        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void edit(Office office) {

        int id = office.getId();
        Office updatedOff = loadById(id);
        updatedOff.setName(office.getName());
        updatedOff.setAddress(office.getAddress());
        if(!updatedOff.getPhone().isEmpty()){
            updatedOff.setPhone(office.getPhone());
        }

        em.merge(updatedOff);
    }

    private CriteriaQuery<Office> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

        Root<Office> person = criteria.from(Office.class);
        criteria.where(builder.equal(person.get("name"), name));

        return criteria;
    }
}
