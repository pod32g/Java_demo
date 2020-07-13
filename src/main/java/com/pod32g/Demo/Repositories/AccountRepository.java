package com.pod32g.Demo.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.pod32g.Demo.Models.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}