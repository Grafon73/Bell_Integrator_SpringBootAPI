package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.userdao.UserDao;
import ru.bellintegrator.practice.model.User;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<User> allOrg() {
        return dao.all();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(User user) {
        dao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User getByID(int id) {
        return dao.loadById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void edit(User user) {
        dao.edit(user);
    }




}
