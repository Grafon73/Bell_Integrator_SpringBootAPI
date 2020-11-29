package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserAddView;
import ru.bellintegrator.practice.user.view.UserFilterViewIn;
import ru.bellintegrator.practice.user.view.UserFilterViewOut;
import ru.bellintegrator.practice.user.view.UserUpdateView;
import ru.bellintegrator.practice.user.view.UserView;

import javax.validation.Valid;
import java.util.List;

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
    public List<UserView> getAllUsers(){
        return userService.allUsers();
    }

    @ApiOperation(value = "Получить человека по ID", httpMethod = "GET")
    @GetMapping("/user/{id}")
    public UserView getUser(@PathVariable String id){
        return userService.getByID(Integer.parseInt(id));
    }

    @ApiOperation(value = "Добавить нового человека", httpMethod = "POST")
    @PostMapping("/user/save")
    public UserAddView addUser(@Valid @RequestBody UserAddView user){
        userService.add(user);
        return user;
    }

    @ApiOperation(value = "Обновить информацию о человеке", httpMethod = "POST")
    @PostMapping("/user/update")
    public UserUpdateView updateUser(@Valid @RequestBody UserUpdateView user){
        userService.edit(user);
        return user;
    }

    @ApiOperation(value = "Получить юзера по фильтру", httpMethod = "POST")
    @PostMapping("/user/list")
    public List<UserFilterViewOut> getByFilter(@Valid @RequestBody UserFilterViewIn user){
        return userService.getByName(user);
    }
}