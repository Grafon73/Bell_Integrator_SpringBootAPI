package ru.bellintegrator.practice.dao.orgdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Organization;

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
    public Organization loadByName(String name) {
        CriteriaQuery<Organization> criteria = buildCriteria(name);
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

    private CriteriaQuery<Organization> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> person = criteria.from(Organization.class);
        criteria.where(builder.equal(person.get("name"), name));

        return criteria;
    }
}
