package com.example.aims.controller;

import com.example.aims.constant.UserRole;
import com.example.aims.entity.user.Admin;
import com.example.aims.entity.user.ProductManager;
import com.example.aims.entity.user.User;
import com.example.aims.exception.user.*;
import com.example.aims.exception.user.create.*;
import com.example.aims.exception.user.login.EmptyInputException;
import com.example.aims.repository.admin.AdminRepository;
import com.example.aims.repository.admin.AdminRepositoryImpl;
import com.example.aims.utils.Utils;

import javax.persistence.NoResultException;
import java.lang.RuntimeException;
import java.util.HashMap;
import java.util.List;

public class CRUDUserController {
    private AdminRepository adminRepository;

    public CRUDUserController() {
        this.adminRepository = new AdminRepositoryImpl();
    }

    public void createUser(String name,
                           String email,
                           String address,
                           String phone,
                           String username,
                           String password,
                           String role,
                           String isBlocked) {
        Utils.processUserInfo(name, email, address, phone, username, password);

        try {
            adminRepository.getUserByUsername(username);
            throw new UsernameExistException("Username already exist");
        } catch (NoResultException e) {
            User savedUser = null;
            if (role.equals("ADMIN")) {
                savedUser = new Admin(name, email, address, phone, username, password, role, isBlocked);
            } else if (role.equals("PRODUCT_MANAGER")) {
                savedUser = new ProductManager(name, email, address, phone, username, password, role, isBlocked);
            }
            adminRepository.addUser(savedUser);
        }
    }

    public List<User> getAllUsers(int userId) {
        return adminRepository.getUsers(userId);
    }

    public void removeUser(User user) {
        adminRepository.removeUser(user);
    }

    public void blockUser(int userId) {
        adminRepository.blockUser(userId);
    }

    public void unblockUser(int userId) {
        adminRepository.unblockUser(userId);
    }

    public void updateUserRole(int userId, UserRole role) {
        adminRepository.updateUserRole(userId, role);
    }

    public void updateUser(User updatedUser, int userId) throws RuntimeException {
        Utils.processUserInfo(
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getAddress(),
                updatedUser.getPhone()
        );
        adminRepository.updateUser(updatedUser, userId);
    }

    public void changePassword(int id, String oldPassword, String newPassword) throws IncorrectOldPasswordException {
        adminRepository.changePassword(id, oldPassword, newPassword);
    }
}
