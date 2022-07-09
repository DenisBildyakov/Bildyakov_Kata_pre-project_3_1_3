package com.bildyakov.bootstrap.repository;


import com.bildyakov.bootstrap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    void deleteById(long id);

    User findUserById(long id);

    UserDetails findUserByEmail(String email);

    User findByEmail(String email);

}
