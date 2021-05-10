package ru.geekbrains.spring.security.repositories;

import ru.geekbrains.spring.security.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
