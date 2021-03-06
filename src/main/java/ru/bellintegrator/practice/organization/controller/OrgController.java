package ru.bellintegrator.practice.organization.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.organization.service.OrgService;
import ru.bellintegrator.practice.organization.view.OrgFilterIn;
import ru.bellintegrator.practice.organization.view.OrgFilterOutView;
import ru.bellintegrator.practice.organization.view.OrgSaveView;
import ru.bellintegrator.practice.organization.view.OrgUpdateView;

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
    public List<OrgUpdateView> getAllOrganizations(){
        return orgService.allOrg();
    }

    @ApiOperation(value = "Получить организацию по ID", httpMethod = "GET")
    @GetMapping("/organization/{id}")
    public OrgUpdateView getOrganization(@PathVariable String id) {
        return orgService.getByID(Integer.parseInt(id));
    }

    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @PostMapping("/organization")
    public OrgSaveView addOrganization(@Valid @RequestBody OrgSaveView organization){
        orgService.add(organization);
        return organization;
    }

    @ApiOperation(value = "Обновить информацию об организации", httpMethod = "POST")
    @PostMapping("/organization/update")
    public OrgUpdateView updateOrganization(@Valid @RequestBody OrgUpdateView organization){
            orgService.edit(organization);
            return organization;
    }

    @ApiOperation(value = "Получить организацию по фильтру", httpMethod = "POST")
    @PostMapping("/organization/list")
    public List<OrgFilterOutView> getOrganizationByFilter(@RequestBody OrgFilterIn organization){
        return orgService.getByName(organization);
    }
}
