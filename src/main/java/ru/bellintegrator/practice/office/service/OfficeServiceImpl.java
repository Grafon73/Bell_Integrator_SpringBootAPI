package ru.bellintegrator.practice.office.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.exceptionhandler.InvalidInputData;
import ru.bellintegrator.practice.exceptionhandler.NoDataFoundException;
import ru.bellintegrator.practice.exceptionhandler.NotFoundException;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeFilterView;
import ru.bellintegrator.practice.office.view.OfficeSaveView;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.dao.OrgDao;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrgDao orgDao;
    private final MapperFactory mapperFactory;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrgDao orgDao, MapperFactory mapperFactory) {
        this.officeDao = officeDao;
        this.orgDao = orgDao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OfficeView> allOrg() {
        List<Office> offices = officeDao.all();
        if (offices.isEmpty()) {

            throw new NoDataFoundException();
        }
        return offices.stream()
                .map(mapperFactory.getMapperFacade(Office.class, OfficeView.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OfficeSaveView office) {
        Organization organization = orgDao.loadById(office.getOrgId());
        if(organization==null){
            throw new InvalidInputData("Organization", "or null Organization ID");
        }
        try {
            Office newOffice = mapperFactory.getMapperFacade().map(office,Office.class);
            newOffice.setOrganization(organization);
            officeDao.save(newOffice);
        }catch (Exception e){
            throw new InvalidInputData("Office", "or null Organization ID");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView getByID(int id) {
        Office office = officeDao.loadById(id);
        if(office==null){
            throw new NotFoundException("Office",id);
        }
        return mapperFactory.getMapperFacade().map(office, OfficeView.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void edit(OfficeView office) {

        try {
            Office newOffice = mapperFactory.getMapperFacade().map(office, Office.class);
            officeDao.edit(newOffice);
        }catch (Exception e){
            throw new InvalidInputData("Office", "or null ID");
        }
}

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OfficeFilterView> getByName(OfficeSaveView office) {
       Office officeEntity = mapperFactory.getMapperFacade().map(office, Office.class);
        if(orgDao.loadById(office.getOrgId())==null){
            throw new InvalidInputData("Office", "null orgId");
        }
        try {
            List<Office> offices = officeDao.loadByName(officeEntity);
            if(offices.isEmpty()){
                throw new NotFoundException("Office");
            }
            return offices.stream()
                    .map(mapperFactory.getMapperFacade(Office.class, OfficeFilterView.class)::map)
                    .collect(Collectors.toList());
        }catch (Exception e){
           throw new NotFoundException("Office");
        }
    }
}
