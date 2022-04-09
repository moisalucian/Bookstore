package com.sda.lucianmoisa.bookstore.repository;

import com.sda.lucianmoisa.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> { //CrudRepository<tipClasa, tipPrimaryKey>

    User findUserByEmail(String email);
}
