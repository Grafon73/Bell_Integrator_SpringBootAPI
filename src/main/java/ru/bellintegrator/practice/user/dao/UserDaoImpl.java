package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.document.model.UserDoc;

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
        int id = user.getId();
        User updatedUser = loadById(id);
        if(user.getOfficeId()!=null){
            updatedUser.setOfficeId(user.getOfficeId());
        }
        updatedUser.setFirstName(user.getFirstName());
        if(!user.getSecondName().isEmpty()){
            updatedUser.setSecondName(user.getSecondName());
        }
        if(!user.getMiddleName().isEmpty()){
            updatedUser.setMiddleName(user.getMiddleName());
        }
        updatedUser.setPosition(user.getPosition());
        if(user.getUserDoc()!=null && user.getUserDoc().getDocCode() != null) {
                updatedUser.getUserDoc().setDocCode(user.getUserDoc().getDocCode());
            }
         em.merge(updatedUser);
    }

    private CriteriaQuery<User> buildCriteriaForFind(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getSecondName();
        String middleName = user.getMiddleName();
        String position = user.getPosition();
        UserDoc userDoc = user.getUserDoc();
        Integer docCode = null;
        if(userDoc != null){
            docCode=userDoc.getDocCode();
        }
        Integer citizenshipCode = user.getCitizenshipCode();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> obj = criteria.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(obj.get("officeId"), user.getOfficeId()));
        if(firstName !=null){
            predicates.add(builder.equal(obj.get("firstName"), firstName));
        }
        if(lastName !=null &&obj.get("secondName")!=null ){
            predicates.add(builder.equal(obj.get("secondName"), lastName));
        }
        if(middleName !=null){
            predicates.add(builder.equal(obj.get("middleName"), middleName));
        }
        if(position !=null){
            predicates.add(builder.equal(obj.get("position"), position));
        }
        if(docCode !=null){
            predicates.add(builder.equal(obj.get("userDoc").get("docCode"), docCode));
        }
        if(citizenshipCode !=null){
            predicates.add(builder.equal(obj.get("citizenshipCode"), citizenshipCode));
        }
        criteria.select(obj)
                .where(predicates.toArray(new Predicate[]{}));

        return criteria;
    }


}