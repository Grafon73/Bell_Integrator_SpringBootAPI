package ru.bellintegrator.practice.user.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.user.view.UserAddView;
import ru.bellintegrator.practice.user.view.UserFilterViewIn;
import ru.bellintegrator.practice.user.view.UserFilterViewOut;
import ru.bellintegrator.practice.user.view.UserUpdateView;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

/**
 * Сервис User
 */
@Validated
public interface UserService {

    /**
     * Получить все объекты User
     */
    List<UserView> allUsers();

    /**
     * Сохранить User
     */
    void add(UserAddView user);

    /**
     * Получить User по идентификатору
     */
    UserView getByID(int id);

    /**
     * Изменить User
     */
    void edit(UserUpdateView user);

    /**
     * Получить User по фильтру
     */
    List<UserFilterViewOut> getByName(UserFilterViewIn user);
}
