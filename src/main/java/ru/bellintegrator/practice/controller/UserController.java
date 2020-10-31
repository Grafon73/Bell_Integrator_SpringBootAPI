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
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.service.UserService;

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
    public List<User> getAllUsers(){
        return userService.allOrg();
    }

    @ApiOperation(value = "Получить человека по ID", httpMethod = "GET")
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id){

        return userService.getByID(Integer.parseInt(id));
    }

    @ApiOperation(value = "Добавить нового человека", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/user/save")
    public User addUser(@RequestBody User user){
        userService.add(user);
        return user;
    }

    @ApiOperation(value = "Обновить информацию о человеке", httpMethod = "POST")
    @PostMapping("/user/update")
    public User updateUser(@RequestBody User user){
        userService.edit(user);
        return user;
    }
}