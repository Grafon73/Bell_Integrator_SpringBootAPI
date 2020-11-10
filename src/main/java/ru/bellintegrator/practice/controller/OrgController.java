package ru.bellintegrator.practice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.OrgService;
import ru.bellintegrator.practice.view.MainDto;
import ru.bellintegrator.practice.view.organization.OrgFilterOutView;
import ru.bellintegrator.practice.view.organization.OrgSaveView;
import ru.bellintegrator.practice.view.organization.OrgUpdateView;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrgController", description = "Управление информацией об организациях")
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OrgController {

    private final OrgService orgService;

    @Autowired
    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }

    @ApiOperation(value = "Получить список всех организаций", httpMethod = "GET")
    @GetMapping("/organization")
    public MainDto getAllOrgs(){
        return new MainDto(orgService.allOrg());
    }

    @ApiOperation(value = "Получить организацию по ID", httpMethod = "GET")
    @GetMapping("/organization/{id}")
    public MainDto getOrg(@PathVariable String id) {
        return new MainDto(orgService.getByID(Integer.parseInt(id)));
    }

    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @PostMapping("/organization")
    public String addOrg(@Valid @RequestBody OrgSaveView organization){
        orgService.add(organization);
        return "{\"result\" : \"success\"}";
    }

    @ApiOperation(value = "Обновить информацию об организации", httpMethod = "POST")
    @PostMapping("/organization/update")
    public String updateOrg(@Valid @RequestBody OrgUpdateView organization){
            orgService.edit(organization);
            return "{\"result\" : \"success\"}";

    }

    @ApiOperation(value = "Получить организацию по фильтру", httpMethod = "POST")
    @PostMapping("/organization/list")
    public List<OrgFilterOutView> getByFilter(@RequestBody Organization organization){
        List<OrgFilterOutView> organizations = orgService.getByName(organization);
        return organizations;
    }

}
