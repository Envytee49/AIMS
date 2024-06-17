package com.example.aims.entity.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_managers")
@NoArgsConstructor
public class ProductManager extends  User{
    public ProductManager(String name, String email, String address, String phone, String username, String password, String role, String userStatus) {
        super(name, email, address, phone, username, password, role, userStatus);
    }
}
