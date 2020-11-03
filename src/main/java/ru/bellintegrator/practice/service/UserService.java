package ru.bellintegrator.practice.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.model.User;

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
    List<User> allOrg();

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
    User  getByID(int id);

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
     * @return
     */
    List<User> getByName(User user);
}
