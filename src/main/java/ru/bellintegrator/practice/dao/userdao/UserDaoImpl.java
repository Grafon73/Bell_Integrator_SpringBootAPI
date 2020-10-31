package ru.bellintegrator.practice.dao.userdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.User;

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
    public User loadByName(String name) {
        CriteriaQuery<User> criteria = buildCriteria(name);
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getSingleResult();
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
        if(user.getDocCode()!=null){
            updatedUser.setDocCode(user.getDocCode());
            updatedUser.setDocName(user.getDocName());
        }

        if(!user.getDocNumber().isEmpty()){
            updatedUser.setDocNumber(user.getDocNumber());
        }
        if(user.getDocDate()!=null){
            updatedUser.setDocDate(user.getDocDate());
        }
        if(user.getCitizenshipCode()!=null){
            updatedUser.setCitizenshipCode(user.getCitizenshipCode());
        }

         em.merge(updatedUser);


    }

    private CriteriaQuery<User> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> user = criteria.from(User.class);
        criteria.where(builder.equal(user.get("name"), name));

        return criteria;
    }


}
