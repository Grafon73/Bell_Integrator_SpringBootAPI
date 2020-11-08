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
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.service.OfficeService;
import ru.bellintegrator.practice.view.MainDto;
import ru.bellintegrator.practice.view.OfficeFilterView;

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
    public MainDto getAllOffices(){
        return new MainDto(officeService.allOrg());
    }

    @ApiOperation(value = "Получить офис по ID", httpMethod = "GET")
    @GetMapping("/office/{id}")
    public MainDto getOffice(@PathVariable String id){
        return new MainDto(officeService.getByID(Integer.parseInt(id)));
    }

    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})

    @PostMapping("/office/save")
    public String addOffice(@RequestBody Office office){
        officeService.add(office);
        return "{\"result\" : \"success\"}";

    }

    @ApiOperation(value = "Обновить информацию об офисе", httpMethod = "POST")
    @PostMapping("/office/update")
    public String updateOffice(@RequestBody Office office){
          officeService.edit(office);
          return "{\"result\" : \"success\"}";
    }

    @ApiOperation(value = "Получить офис по фильтру", httpMethod = "POST")
    @PostMapping("/office/list")
    public MainDto getOrg(@RequestBody Office office){

        List<OfficeFilterView> offices = officeService.getByName(office);
         return new MainDto(offices);
    }

}