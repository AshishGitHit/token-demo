package com.example.jwt.token.demo.repo;

import com.example.jwt.token.demo.entity.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<MyUser, String> {
}
