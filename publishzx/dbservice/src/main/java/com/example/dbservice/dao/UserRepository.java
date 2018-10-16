package com.example.dbservice.dao;

import com.example.dbservice.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User findByName(String name);
    User findByEmail(String email);
}
