package ru.bellintegrator.practice.organization.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.exceptionhandler.InvalidInputData;
import ru.bellintegrator.practice.exceptionhandler.NoDataFoundException;
import ru.bellintegrator.practice.exceptionhandler.NotFoundException;
import ru.bellintegrator.practice.organization.dao.OrgDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrgFilterIn;
import ru.bellintegrator.practice.organization.view.OrgFilterOutView;
import ru.bellintegrator.practice.organization.view.OrgSaveView;
import ru.bellintegrator.practice.organization.view.OrgUpdateView;

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
    public List<OrgUpdateView> allOrg() {
        List<Organization> orgs = dao.all();
        if (orgs.isEmpty()) {
            throw new NoDataFoundException();
        }
        return orgs.stream()
                .map(mapperFactory.getMapperFacade(Organization.class, OrgUpdateView.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     * @param organization
     */
    @Override
    @Transactional
    public void add(OrgSaveView organization) {
         Organization newOrganization = mapperFactory.getMapperFacade().map(organization, Organization.class);
         dao.save(newOrganization);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public OrgUpdateView getByID(int id) {
        Organization organization = dao.loadById(id);
        if(organization==null){
            throw new NotFoundException("Organization",id);
        }
      return mapperFactory.getMapperFacade().map(organization, OrgUpdateView.class);
    }

    /**
     * {@inheritDoc}
     * @param organization
     */
    @Override
    @Transactional
    public void edit(OrgUpdateView organization) {
        try {
            Organization newOrganization = mapperFactory.getMapperFacade().map(organization, Organization.class);
            dao.edit(newOrganization);
        }catch (Exception e){
            throw new InvalidInputData("Organization", "or null ID");
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<OrgFilterOutView> getByName(OrgFilterIn organization) {
        Organization orgEntity = mapperFactory.getMapperFacade().map(organization,Organization.class);
        String name = organization.getName();
        if(name==null){
            throw new InvalidInputData("Organization", "null name");
        }
        try {
            List<Organization> loadedOrg = dao.loadByName(orgEntity);
            if(loadedOrg.isEmpty()){
                throw new NotFoundException("Organization");
            }
            return loadedOrg.stream()
                    .map(mapperFactory.getMapperFacade(Organization.class, OrgFilterOutView.class)::map)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new NotFoundException("Organization");
        }
    }
}
