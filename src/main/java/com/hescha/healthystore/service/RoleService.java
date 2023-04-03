package com.hescha.healthystore.service;

import com.hescha.healthystore.model.Role;
import com.hescha.healthystore.model.User;
import com.hescha.healthystore.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends CrudService<Role> {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Role> findByRole(String role) {
        return repository.findByRole(role);
    }

    public List<Role> findByRoleContains(String role) {
        return repository.findByRoleContains(role);
    }

    public List<Role> findByUsersContains(User users) {
        return repository.findByUsersContains(users);
    }


    public Role update(Long id, Role entity) {
        Role read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Role not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Role entity, Role read) {
        read.setRole(entity.getRole());
        read.setUsers(entity.getUsers());
    }
}
