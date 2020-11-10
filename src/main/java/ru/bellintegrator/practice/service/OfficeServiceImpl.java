package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.officedao.OfficeDao;
import ru.bellintegrator.practice.exception.InvalidInputData;
import ru.bellintegrator.practice.exception.NoDataFoundException;
import ru.bellintegrator.practice.exception.NotFoundException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeFilterView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao dao;
    private final MapperFactory mapperFactory;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, MapperFactory mapperFactory) {
        this.dao = dao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OfficeView> allOrg() {
        List<Office> offices = dao.all();
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
        try {
            Office newOffice = mapperFactory.getMapperFacade().map(office,Office.class);
            dao.save(newOffice);
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
        Office office = dao.loadById(id);
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
            dao.edit(newOffice);
        }catch (Exception e){
            throw new InvalidInputData("Office", "or null ID");
        }
}

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OfficeFilterView> getByName(Office office) {
        if(office.getOrgId()==null){
            throw new InvalidInputData("Office", "null orgId");
        }
        try {
            List<Office> offices = dao.loadByName(office);
            if(offices.size()<1){
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
