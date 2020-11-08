package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.orgdao.OrgDao;
import ru.bellintegrator.practice.exception.InvalidInputData;
import ru.bellintegrator.practice.exception.NoDataFoundException;
import ru.bellintegrator.practice.exception.NotFoundException;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrgFilterView;
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
        if (orgs.isEmpty()) {

            throw new NoDataFoundException();
        }
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
     try {
         dao.save(organization);
     }catch (Exception e){
         throw new InvalidInputData("Organization");
     }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public OrgView getByID(int id) {
        Organization organization = dao.loadById(id);
        if(organization==null){
            throw new NotFoundException("Organization",id);
        }

      return mapperFactory.getMapperFacade().map(organization, OrgView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void edit(Organization organization) {
        try {
            dao.edit(organization);
        }catch (Exception e){
            throw new InvalidInputData("Organization");
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<OrgFilterView> getByName(Organization organization) {
        String name = organization.getName();
        if(name==null){
            throw new InvalidInputData("Organization", "name");
        }
        try {
            List<Organization> loadedOrg = dao.loadByName(organization);
            if(loadedOrg.size()<1){
                throw new NotFoundException("Organization");
            }
            return loadedOrg.stream()
                    .map(mapperFactory.getMapperFacade(Organization.class, OrgFilterView.class)::map)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new NotFoundException("Organization");
        }
    }
}
