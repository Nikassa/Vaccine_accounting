package ru.my.task.vaccine_accounting.service.auth;

import ru.my.task.vaccine_accounting.model.auth.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
