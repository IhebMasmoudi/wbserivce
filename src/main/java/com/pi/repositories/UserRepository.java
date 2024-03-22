package com.pi.repositories;

import com.pi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);


    Optional<User> findByUsername(String username);


}
