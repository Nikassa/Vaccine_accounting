package ru.my.task.vaccine_accounting.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.my.task.vaccine_accounting.model.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
