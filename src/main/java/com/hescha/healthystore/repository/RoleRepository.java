package com.hescha.healthystore.repository;

import com.hescha.healthystore.model.Role;
import com.hescha.healthystore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRole(String role);

    List<Role> findByRoleContains(String role);

    List<Role> findByUsersContains(User users);
}
