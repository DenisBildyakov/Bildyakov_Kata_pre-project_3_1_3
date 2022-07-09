package com.bildyakov.bootstrap.repository;

import com.bildyakov.bootstrap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
    Collection<Role> findAllByUsersId(long id);
}
