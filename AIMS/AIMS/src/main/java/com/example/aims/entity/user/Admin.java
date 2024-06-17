package com.example.aims.entity.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrators")
@NoArgsConstructor
public class Admin extends User {
    public Admin(String name, String email, String address, String phone, String username, String password, String role, String userStatus) {
        super(name, email, address, phone, username, password, role, userStatus);
    }
}

