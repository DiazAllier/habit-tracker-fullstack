package com.diaza.habitracker.services;

import com.diaza.habitracker.dao.IUserDao;
import com.diaza.habitracker.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {


    @Autowired
    private IUserDao userDao;

    @Transactional
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Transactional
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}
