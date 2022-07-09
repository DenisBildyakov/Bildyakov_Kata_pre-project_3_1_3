package com.bildyakov.bootstrap.service;


import com.bildyakov.bootstrap.model.Role;
import com.bildyakov.bootstrap.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findBiId(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll(Sort.by(Sort.Direction.ASC, "role"));
    }


}
