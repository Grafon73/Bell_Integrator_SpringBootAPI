package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

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
    List<UserView> allOrg();

    /**
     * Сохранить User
     *
     * @param user
     */
    void add(User user);

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
    void edit(User user);

    /**
     * Получить User по фильтру
     *
     * @param user
     * @return List<User>
     */
    List<UserFilterView> getByName(User user);
}
