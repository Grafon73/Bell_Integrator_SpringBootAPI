package ru.bellintegrator.practice.document.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.document.service.InfoService;
import ru.bellintegrator.practice.document.view.CountryView;
import ru.bellintegrator.practice.document.view.DocView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "InformationController", description = "Справочники")
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class InformationController {

    private final InfoService infoService;

    @Autowired
    public InformationController(InfoService infoService) {
        this.infoService = infoService;
    }
    @ApiOperation(value = "Получить список всех документов", httpMethod = "GET")

    @GetMapping("/docs")
    public List<DocView> getAllDocs(){
        return infoService.allDocs();
    }

    @ApiOperation(value = "Получить список всех стран", httpMethod = "GET")
    @GetMapping("/countries")
    public List<CountryView> getAllCountries(){
        return infoService.allCountries();
    }

}
