package ru.my.task.vaccine_accounting.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.my.task.vaccine_accounting.model.auth.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);
}
