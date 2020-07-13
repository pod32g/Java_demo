package com.pod32g.Demo.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pod32g.Demo.Models.Account;
import com.pod32g.Demo.Repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vavr.control.Either;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();

        accountRepository.findAll().forEach(account -> accounts.add(account));

        return accounts;
    }

    public Either<Account, String> getAccountByID(int id) {

        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            return Either.right("Account not found");
        }

        return Either.left(account.get());
    }

    public Account updateOrSave(Account account) {
        return accountRepository.save(account);
    }

    public Either<Boolean, String> deleteById(int id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            return Either.right("Account not found");
        }

        accountRepository.delete(account.get());

        return Either.left(true);
    }

}