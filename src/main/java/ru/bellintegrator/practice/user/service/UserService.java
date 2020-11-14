package ru.bellintegrator.practice.user.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.user.view.UserAddView;
import ru.bellintegrator.practice.user.view.UserFilterViewIn;
import ru.bellintegrator.practice.user.view.UserFilterViewOut;
import ru.bellintegrator.practice.user.view.UserUpdateView;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface UserService {

    /**
     * Получить все объекты User
     *
     * @return List<UserView>
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
     * @return UserView
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
     * @return List<UserFilterViewOut>
     */
    List<UserFilterViewOut> getByName(UserFilterViewIn user);
}
