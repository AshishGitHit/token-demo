package com.example.jwt.token.demo.repo;

import com.example.jwt.token.demo.entity.MyRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<MyRole, String> {
}
