package com.bildyakov.bootstrap.service;

import com.bildyakov.bootstrap.model.Role;

import java.util.List;

public interface RoleService {
    Role findBiId(int id);

    Role findByRole(String role);

    List<Role> findAllRoles();
}
