package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.userdao.UserDao;
import ru.bellintegrator.practice.exception.InvalidInputData;
import ru.bellintegrator.practice.exception.NoDataFoundException;
import ru.bellintegrator.practice.exception.NotFoundException;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final MapperFactory mapperFactory;


    @Autowired
    public UserServiceImpl(UserDao dao,MapperFactory mapperFactory) {
        this.mapperFactory=mapperFactory;
        this.dao = dao;

    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public List<UserView> allOrg() {
        List<User> users = dao.all();
        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }
        mapperFactory
                .classMap(User.class, UserView.class)
                .field("userDoc.doc.name","docName")
                .field("userDoc.docNumber","docNumber")
                .field("userDoc.docDate","docDate")
                .field("country.name","citizenshipName")
                .byDefault()
                .register();
        return users.stream()
                .map(mapperFactory.getMapperFacade(User.class, UserView.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(User user) {
        try {
            dao.save(user);
        }catch (Exception e){
            throw new InvalidInputData("User");
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public UserView getByID(int id) {
        User user = dao.loadById(id);
        if(user==null){
            throw new NotFoundException("User",id);
        }
        mapperFactory
                .classMap(User.class, UserView.class)
                .field("userDoc.doc.name","docName")
                .field("userDoc.docNumber","docNumber")
                .field("userDoc.docDate","docDate")
                .field("country.name","citizenshipName")
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade().map(user, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void edit(User user) {
        try {
            dao.edit(user);
        }catch (Exception e){
            throw new InvalidInputData("User");
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<UserFilterView> getByName(User user) {

        Integer officeId = user.getOfficeId();
        if(officeId==null){
            throw new InvalidInputData("User", "officeId");
        }
        try {
            List<User> loadedOrg = dao.loadByName(user);
            if(loadedOrg.size()<1){
                throw new NotFoundException("User");
            }
            return loadedOrg.stream()
                    .map(mapperFactory.getMapperFacade(User.class, UserFilterView.class)::map)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new NotFoundException("User");
        }
    }


}
