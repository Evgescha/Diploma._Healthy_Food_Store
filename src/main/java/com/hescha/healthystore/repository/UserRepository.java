package com.hescha.healthystore.repository;

import com.hescha.healthystore.model.Order;
import com.hescha.healthystore.model.Role;
import com.hescha.healthystore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameContains(String username);

    List<User> findByPassword(String password);

    List<User> findByPasswordContains(String password);

    List<User> findByFirstname(String firstname);

    List<User> findByFirstnameContains(String firstname);

    List<User> findByLastname(String lastname);

    List<User> findByLastnameContains(String lastname);

    List<User> findByEmail(String email);

    List<User> findByEmailContains(String email);

    List<User> findByImage(String image);

    List<User> findByImageContains(String image);

    List<User> findByAddress(String address);

    List<User> findByAddressContains(String address);

    List<User> findByRolesContains(Role roles);

    List<User> findByOrdersContains(Order orders);
}
