package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.officedao.OfficeDao;
import ru.bellintegrator.practice.model.Office;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao dao;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao) {
        this.dao = dao;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Office> allOrg() {
      return dao.all();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(Office office) {
        dao.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Office getByID(int id) {
        return dao.loadById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void edit(Office office) {
        dao.edit(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> getByName(Office office) {
        return dao.loadByName(office);
    }
}
