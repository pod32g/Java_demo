package com.pod32g.Demo.Repositories;

import com.pod32g.Demo.Models.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}