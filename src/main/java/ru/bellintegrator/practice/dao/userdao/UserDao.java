package ru.bellintegrator.practice.dao.userdao;

import ru.bellintegrator.practice.model.User;

import java.util.List;

/**
 * DAO для работы с Person
 */
public interface UserDao {
    /**
     * Получить все объекты User
     *
     * @return
     */
    List<User> all();

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(int id);

    /**
     * Получить User по фильтру
     *
     * @param name
     * @return
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
