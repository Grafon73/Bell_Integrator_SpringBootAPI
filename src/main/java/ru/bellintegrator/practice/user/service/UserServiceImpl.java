package ru.bellintegrator.practice.user.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.exceptionhandler.InvalidInputData;
import ru.bellintegrator.practice.exceptionhandler.NoDataFoundException;
import ru.bellintegrator.practice.exceptionhandler.NotFoundException;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserAddView;
import ru.bellintegrator.practice.user.view.UserFilterViewIn;
import ru.bellintegrator.practice.user.view.UserFilterViewOut;
import ru.bellintegrator.practice.user.view.UserUpdateView;
import ru.bellintegrator.practice.user.view.UserView;

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
    public List<UserView> allUsers() {
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

        return mapperFactory.getMapperFacade().mapAsList(users, UserView.class);
    }

    /**
     * {@inheritDoc}
     * @param user
     */
    @Override
    @Transactional
    public void add(UserAddView user) {
         try {
             mapperFactory
                     .classMap(UserAddView.class, User.class)
                     .field("docDate", "userDoc.docDate")
                     .field("docNumber", "userDoc.docNumber")
                     .field("docCode", "userDoc.docCode")
                     .byDefault()
                     .register();

             User newUser = mapperFactory.getMapperFacade().map(user, User.class);
             dao.save(newUser);
         }catch (Exception e){
             throw new InvalidInputData("User", "or null ID");
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
     * @param user
     */
    @Override
    @Transactional
    public void edit(UserUpdateView user) {
            User newUser = mapperFactory.getMapperFacade().map(user, User.class);
            dao.edit(newUser);
    }

    /**
     * {@inheritDoc}
     * @return
     * @param user
     */
    @Override
    public List<UserFilterViewOut> getByName(UserFilterViewIn user) {


            User newUser = mapperFactory.getMapperFacade().map(user, User.class);
            List<User> loadedOrg = dao.loadByName(newUser);
            if(loadedOrg.isEmpty()){
                throw new NotFoundException("User");

            }
            return loadedOrg.stream()
                    .map(mapperFactory.getMapperFacade(User.class, UserFilterViewOut.class)::map)
                    .collect(Collectors.toList());

    }

}
