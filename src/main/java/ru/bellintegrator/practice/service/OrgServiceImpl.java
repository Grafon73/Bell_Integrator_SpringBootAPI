package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.orgdao.OrgDao;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.DocView;
import ru.bellintegrator.practice.view.OrgView;

import java.util.List;
import java.util.stream.Collectors;


/**
 * {@inheritDoc}
 */
@Service
public class OrgServiceImpl implements OrgService {
    private final OrgDao dao;
    private final MapperFactory mapperFactory;


    @Autowired
    public OrgServiceImpl(OrgDao dao, MapperFactory mapperFactory) {
        this.mapperFactory=mapperFactory;
        this.dao=dao;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public List<OrgView> allOrg() {
        List<Organization> orgs = dao.all();
        return orgs.stream()
                .map(mapperFactory.getMapperFacade(Organization.class, OrgView.class)::map)
                .collect(Collectors.toList());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getByName(Organization organization) {
        return dao.loadByName(organization);
    }
}
