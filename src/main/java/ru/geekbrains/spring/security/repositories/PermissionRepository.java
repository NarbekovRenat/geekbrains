package ru.geekbrains.spring.security.repositories;

import ru.geekbrains.spring.security.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findAllByRoleId(Long id);
}
