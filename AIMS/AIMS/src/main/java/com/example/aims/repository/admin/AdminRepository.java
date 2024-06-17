package com.example.aims.repository.admin;

import com.example.aims.constant.UserRole;
import com.example.aims.entity.user.User;
import com.example.aims.exception.user.IncorrectOldPasswordException;

import java.util.List;

public interface AdminRepository {
    // TODO all the user method
    List<User> getUsers(int userId);
    void addUser(User user);
    void removeUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    void updateUser(User updateUser, int userId);
    void blockUser(int id);
    void unblockUser(int id);
    void changePassword(int id, String oldPassword, String newPassword) throws IncorrectOldPasswordException;
    void updateUserRole(int id, UserRole role);
}
