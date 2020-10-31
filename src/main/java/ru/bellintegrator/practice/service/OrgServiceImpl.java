package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.orgdao.OrgDao;
import ru.bellintegrator.practice.model.Organization;

import java.util.List;


/**
 * {@inheritDoc}
 */
@Service
public class OrgServiceImpl implements OrgService {
    private final OrgDao dao;

    @Autowired
    public OrgServiceImpl(OrgDao dao) {
        this.dao = dao;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Organization> allOrg() {
        return dao.all();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(Organization organization) {
      dao.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Organization getByID(int id) {
        return dao.loadById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void edit(Organization organization) {
      dao.edit(organization);
    }
}
