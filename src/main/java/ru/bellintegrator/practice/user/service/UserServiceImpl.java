package ru.bellintegrator.practice.user.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.document.dao.InfoDao;
import ru.bellintegrator.practice.document.model.Country;
import ru.bellintegrator.practice.document.model.Doc;
import ru.bellintegrator.practice.exceptionhandler.InvalidInputData;
import ru.bellintegrator.practice.exceptionhandler.NoDataFoundException;
import ru.bellintegrator.practice.exceptionhandler.NotFoundException;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.model.UserDoc;
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
    private final InfoDao infoDao;

    @Autowired
    public UserServiceImpl(UserDao dao,MapperFactory mapperFactory,InfoDao infoDao) {
        this.mapperFactory=mapperFactory;
        this.dao = dao;
        this.infoDao = infoDao;
    }

    /**
     * {@inheritDoc}
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
                .field("country.code","citizenshipCode")
                .field("country.name","citizenshipName")
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade().mapAsList(users, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(UserAddView user) {
         try {
             mapperFactory
                     .classMap(UserAddView.class, User.class)
                     .field("docDate", "userDoc.docDate")
                     .field("docNumber", "userDoc.docNumber")
                     .field("docCode", "userDoc.doc.code")
                     .field("citizenshipCode","country.code")
                     .byDefault()
                     .register();
             User newUser = mapperFactory.getMapperFacade().map(user, User.class);
        if (user.getCitizenshipCode() != null) {
            Country citizenship = infoDao.loadCountryByCode(user.getCitizenshipCode());
            if(citizenship==null){
                throw new NotFoundException("This Country");
            }
            newUser.setCountry(citizenship);
        }
        UserDoc newUserUserDoc = newUser.getUserDoc();
        if(newUserUserDoc!=null) {
            newUserUserDoc.setUser(newUser);
                Doc doc = new Doc();
                if (user.getDocCode() != null) {
                    doc = infoDao.loadDocByCode(user.getDocCode());
                    newUserUserDoc.setDoc(doc);
                }
                if (doc == null) {
                    throw new NotFoundException("This Document Code");
                }
                newUser.setUserDoc(newUserUserDoc);
        }
             dao.save(newUser);
         }catch (Exception e){
             throw new InvalidInputData("User", "or null input");
         }
    }

    /**
     * {@inheritDoc}
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
                .field("country.code","citizenshipCode")
                .byDefault()
                .register();
        return mapperFactory.getMapperFacade().map(user, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void edit(UserUpdateView user) {
        mapperFactory
                .classMap(UserUpdateView.class, User.class)
                .field("docDate", "userDoc.docDate")
                .field("docNumber", "userDoc.docNumber")
                .field("docCode", "userDoc.doc.code")
                .field("docName", "userDoc.doc.name")
                .field("citizenshipCode", "country.code")
                .byDefault()
                .register();
        User newUser = mapperFactory.getMapperFacade().map(user, User.class);
        Integer id = newUser.getId();
        User updatedUser = dao.loadById(id);
        if (updatedUser == null) {
            throw new NotFoundException("Organization", id);
        }
        if (newUser.getOfficeId() != null) {
            updatedUser.setOfficeId(newUser.getOfficeId());
        }
        updatedUser.setFirstName(newUser.getFirstName());
        if (newUser.getSecondName()!=null) {
            updatedUser.setSecondName(newUser.getSecondName());
        }
        if (newUser.getMiddleName()!=null) {
            updatedUser.setMiddleName(newUser.getMiddleName());
        }
        updatedUser.setPosition(newUser.getPosition());
        if (newUser.getUserDoc() != null) {
            UserDoc newUserDoc = updatedUser.getUserDoc();
            if (user.getDocCode() != null) {
                Doc doc = infoDao.loadDocByCode(user.getDocCode());
                if (doc == null) {
                    throw new NotFoundException("This Document Code");
                }
                newUserDoc.setDoc(doc);
                newUserDoc.setDocNumber(user.getDocNumber());
                newUserDoc.setDocDate(user.getDocDate());
            }
            newUserDoc.setUser(updatedUser);
            updatedUser.setUserDoc(newUserDoc);
        }
            if (newUser.getCountry() != null && newUser.getCountry().getCode() != null) {
                    Country citizenship = infoDao.loadCountryByCode(newUser.getCountry().getCode());
                    if(citizenship==null){
                        throw new NotFoundException("This Country");
                    }
                updatedUser.setCountry(citizenship);
            }
            try {
                dao.edit(updatedUser);
            } catch (Exception e) {
                throw new InvalidInputData("input", "User");
            }
        }


    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<UserFilterViewOut> getByName(UserFilterViewIn user) {
        mapperFactory
                .classMap(UserFilterViewIn.class, User.class)
                .field("docCode", "userDoc.doc.code")
                .field("citizenshipCode", "country.code")
                .byDefault()
                .register();

            User newUser = mapperFactory.getMapperFacade().map(user, User.class);

            List<User> loadedOrg = dao.loadByName(newUser);
            if(loadedOrg.isEmpty()){
                throw new NotFoundException("User");
            }
        mapperFactory
                .classMap(User.class, UserView.class)
                .field("userDoc.doc.code","docName")
                .field("country.code","citizenshipCode")
                .byDefault()
                .register();
            return loadedOrg.stream()
                    .map(mapperFactory.getMapperFacade(User.class, UserFilterViewOut.class)::map)
                    .collect(Collectors.toList());
    }
}
