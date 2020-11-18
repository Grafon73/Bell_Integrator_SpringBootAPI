package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;

import java.util.List;

/**
 * DAO для работы с Person
 */
public interface UserDao {
    /**
     * Получить все объекты User
     *
     * @return List<User>
     */
    List<User> all();

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return User
     */
    User loadById(int id);

    /**
     * Получить User по фильтру
     *
     * @param user
     * @return List<User>
     */
    List<User> loadByName(User user);

    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);

    /**
     * Изменить User
     *
     * @param user
     */
    void edit(User user);

}
