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
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.MainDto;
import ru.bellintegrator.practice.view.user.UserAddView;
import ru.bellintegrator.practice.view.user.UserFilterViewIn;
import ru.bellintegrator.practice.view.user.UserUpdateView;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "UserController", description = "Управление информацией о людях")
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Получить список всех людей", httpMethod = "GET")
    @GetMapping("/user")
    public MainDto getAllUsers(){
        return new MainDto(userService.allUsers());
    }

    @ApiOperation(value = "Получить человека по ID", httpMethod = "GET")
    @GetMapping("/user/{id}")
    public MainDto getUser(@PathVariable String id){

        return new MainDto(userService.getByID(Integer.parseInt(id)));
    }

    @ApiOperation(value = "Добавить нового человека", httpMethod = "POST")
    @PostMapping("/user/save")
    public String addUser(@Valid @RequestBody UserAddView user){
        userService.add(user);
        return "{\"result\" : \"success\"}";
    }

    @ApiOperation(value = "Обновить информацию о человеке", httpMethod = "POST")
    @PostMapping("/user/update")
    public String updateUser(@Valid @RequestBody UserUpdateView user){
        userService.edit(user);
        return "{\"result\" : \"success\"}";
    }

    @ApiOperation(value = "Получить юзера по фильтру", httpMethod = "POST")
    @PostMapping("/user/list")
    public MainDto getByFilter(@Valid @RequestBody UserFilterViewIn user){
        return new MainDto(userService.getByName(user));
    }
}