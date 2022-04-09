package com.sda.lucianmoisa.bookstore.service;

import com.sda.lucianmoisa.bookstore.model.Role;
import com.sda.lucianmoisa.bookstore.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void saveUser(String email, String password, Role role);

    User findByEmail(String email);
}
