package ru.bellintegrator.practice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.OrgService;
import ru.bellintegrator.practice.view.OrgView;

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
    public List<OrgView> getAllOrgs(){
        return orgService.allOrg();
    }

    @ApiOperation(value = "Получить организацию по ID", httpMethod = "GET")
    @GetMapping("/organization/{id}")
    public Organization getOrg(@PathVariable String id){

        return orgService.getByID(Integer.parseInt(id));
    }

    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/organization")
    public Organization addOrg(@RequestBody Organization organization){
        orgService.add(organization);
        return organization;
    }

    @ApiOperation(value = "Обновить информацию об организации", httpMethod = "POST")
    @PostMapping("/organization/update")
    public Organization updateOrg(@RequestBody Organization organization){
        orgService.edit(organization);
        return organization;
    }

    @ApiOperation(value = "Получить организацию по фильтру", httpMethod = "POST")
    @PostMapping("/organization/list")
    public Organization getOrg(@RequestBody Organization organization){

        return orgService.getByName(organization);
    }

}
