package com.diaza.habitracker.dao;

import com.diaza.habitracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface IUserDao extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
