package com.example.aims.controller;


import com.example.aims.constant.UserRole;
import com.example.aims.constant.UserStatus;
import com.example.aims.entity.user.User;
import com.example.aims.exception.user.login.EmptyInputException;
import com.example.aims.exception.user.login.NonExistUserException;
import com.example.aims.exception.user.login.UserIsBlockedException;
import com.example.aims.exception.user.login.WrongPasswordException;
import com.example.aims.repository.admin.AdminRepository;
import com.example.aims.repository.admin.AdminRepositoryImpl;

import javax.persistence.NoResultException;

public class LogInController {

    private AdminRepository adminRepository;

    public LogInController() {
        this.adminRepository = new AdminRepositoryImpl();
    }

    public User logIn(String username, String password)
            throws EmptyInputException, NonExistUserException, UserIsBlockedException, WrongPasswordException {
        if (username.isBlank() || password.isBlank()) {
            throw new EmptyInputException("Username or password is empty");
        }
        try {
            User user = adminRepository.getUserByUsername(username);
            if (user.getPassword().equals(password)) {
                if (user.getUserStatus() == UserStatus.BLOCKED)
                    throw new UserIsBlockedException("User Is BLocked ERROR");
                return user;
            } else {
                throw new WrongPasswordException("Wrong Password ERROR");
            }
        } catch (NoResultException e) {
            throw new NonExistUserException("User Not Found");
        }
    }
}
