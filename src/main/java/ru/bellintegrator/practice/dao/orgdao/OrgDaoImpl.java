package ru.bellintegrator.practice.dao.orgdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * {@inheritDoc}
 */
@Repository
public class OrgDaoImpl implements OrgDao {

    private final EntityManager em;

    @Autowired
    public OrgDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(int id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadByName(Organization organization) {
        CriteriaQuery<Organization> criteria = buildCriteria(organization);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization org) {
         em.persist(org);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void edit(Organization organization) {
        int id = organization.getId();
        Organization updatedOrg = loadById(id);
        updatedOrg.setName(organization.getName());
        updatedOrg.setFullName(organization.getFullName());
        updatedOrg.setInn(organization.getInn());
        updatedOrg.setKpp(organization.getKpp());
        updatedOrg.setAddress(organization.getAddress());
    if(!organization.getPhone().isEmpty()){
        updatedOrg.setPhone(organization.getPhone());
    }
        em.merge(updatedOrg);
    }

    private CriteriaQuery<Organization> buildCriteria(Organization organization) {
        String phone= organization.getPhone();
        Boolean isActive = organization.getisActive();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> obj = criteria.from(Organization.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(builder.equal(obj.get("name"), organization.getName()));
        if(phone !=null){
            predicates.add(builder.equal(obj.get("phone"), phone));
        }
        if(isActive != null){
            predicates.add(builder.equal(obj.get("IsActive"), isActive));
        }
        criteria.select(obj)
                .where(predicates.toArray(new Predicate[]{}));

        return criteria;
    }
}
