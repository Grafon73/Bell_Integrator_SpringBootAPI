package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.model.UserDoc;

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
public class UserDaoImpl implements UserDao{

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> all() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(int id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> loadByName(User user) {
        CriteriaQuery<User> criteria = buildCriteriaForFind(user);
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
       em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void edit(User user) {
         em.merge(user);
    }

    private CriteriaQuery<User> buildCriteriaForFind(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getSecondName();
        String middleName = user.getMiddleName();
        String position = user.getPosition();
        UserDoc userDoc = user.getUserDoc();
        Integer docCode = null;
        if(userDoc != null){
            docCode=userDoc.getDoc().getCode();
        }
        Integer citizenshipCode = user.getCountry().getCode();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> obj = criteria.from(User.class);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate,builder.equal(obj.get("officeId"), user.getOfficeId()));

        if(firstName !=null){
            predicate = builder.and(predicate,builder.equal(obj.get("firstName"), firstName));
        }
        if(lastName !=null &&obj.get("secondName")!=null ){
            predicate = builder.and(predicate,builder.equal(obj.get("secondName"), lastName));
        }
        if(middleName !=null){
            predicate = builder.and(predicate,builder.equal(obj.get("middleName"), middleName));
        }
        if(position !=null){
            predicate = builder.and(predicate,builder.equal(obj.get("position"), position));
        }
        if(docCode !=null){
            predicate = builder.and(predicate,builder.equal(obj.get("userDoc").get("doc").get("code"), docCode));
        }
        if(citizenshipCode !=null){
            predicate = builder.and(predicate,builder.equal(obj.get("country").get("code"), citizenshipCode));
        }
        criteria.select(obj).where(predicate);

        return criteria;
    }
}
