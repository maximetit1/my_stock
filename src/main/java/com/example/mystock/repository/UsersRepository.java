package com.example.mystock.repository;

import com.example.mystock.model.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface UsersRepository extends CrudRepository<User, BigDecimal> {
    User findByUsername(String username);
}
