package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeFilterView;
import ru.bellintegrator.practice.office.view.OfficeSaveView;
import ru.bellintegrator.practice.office.view.OfficeView;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OfficeController", description = "Управление информацией об офисах")
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Получить список всех офисов", httpMethod = "GET")
    @GetMapping("/office")
    public List<OfficeView> getAllOffices(){
        return officeService.allOrg();
    }

    @ApiOperation(value = "Получить офис по ID", httpMethod = "GET")
    @GetMapping("/office/{id}")
    public OfficeView getOffice(@PathVariable String id){
        return officeService.getByID(Integer.parseInt(id));
    }

    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    @PostMapping("/office/save")
    public OfficeSaveView addOffice(@Valid @RequestBody OfficeSaveView office){
        officeService.add(office);
        return office;

    }

    @ApiOperation(value = "Обновить информацию об офисе", httpMethod = "POST")
    @PostMapping("/office/update")
    public OfficeView updateOffice(@Valid @RequestBody OfficeView office){
          officeService.edit(office);
        return office;
    }

    @ApiOperation(value = "Получить офис по фильтру", httpMethod = "POST")
    @PostMapping("/office/list")
    public List<OfficeFilterView> getByFilter(@RequestBody OfficeSaveView office){
         return officeService.getByName(office);
    }

}