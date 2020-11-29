package ru.bellintegrator.practice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
    public List<Office> loadByName(Office office) {
        CriteriaQuery<Office> criteria = buildCriteria(office);
        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();
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
        if(updatedOff.getPhone()==null) {
            updatedOff.setPhone(office.getPhone());
        }
        em.merge(updatedOff);
    }
    private CriteriaQuery<Office> buildCriteria(Office office) {
        String name = office.getName();
        String phone = office.getPhone();
        Boolean isActive = office.getIsActive();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> obj = criteria.from(Office.class);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate,builder.equal(obj.get("orgId"), office.getOrgId()));
        if(name !=null){
            predicate = builder.and(predicate,builder.equal(obj.get("name"), name));
        }
        if(phone !=null){
            predicate = builder.and(predicate,builder.equal(obj.get("phone"), phone));
        }
        if(isActive != null){
            predicate = builder.and(predicate,builder.equal(obj.get("isActive"), isActive));
        }
        criteria.select(obj).where(predicate);
        return criteria;
    }
}
