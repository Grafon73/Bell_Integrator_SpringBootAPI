package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.user.UserAddView;
import ru.bellintegrator.practice.view.user.UserFilterViewIn;
import ru.bellintegrator.practice.view.user.UserFilterViewOut;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface UserService {

    /**
     * Получить все объекты User
     *
     * @return
     */
    List<UserView> allUsers();

    /**
     * Сохранить User
     *
     * @param user
     */
    void add(UserAddView user);

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    UserView getByID(int id);

    /**
     * Изменить User
     *
     * @param user
     */
    void edit(UserUpdateView user);

    /**
     * Получить User по фильтру
     *
     * @param user
     * @return List<User>
     */
    List<UserFilterViewOut> getByName(UserFilterViewIn user);
}
