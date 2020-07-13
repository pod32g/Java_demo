package com.pod32g.Demo.Services;

import com.pod32g.Demo.Repositories.AccountRepository;
import com.pod32g.Demo.Repositories.UserRepository;
import com.pod32g.Demo.Utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pod32g.Demo.Models.Account;
import com.pod32g.Demo.Models.User;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));

        return users;
    }

    public Either<User, String> getUserById(int id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return Either.right("User not found");
        }

        return Either.left(user.get());
    }

    public User updateOrSave(User user) {
        return userRepository.save(user);
    }

    public Either<Boolean, String> deleteById(int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return Either.right("User not found");
        }

        userRepository.delete(user.get());

        return Either.left(true);
    }

    public Either<User, String> addAccountToUser(int account_id, int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        Optional<Account> account = accountRepository.findById(account_id);

        if (user.isEmpty()) {
            return Either.right("User not found");
        } else if (account.isEmpty()) {
            return Either.right("Account not found");
        }

        user.get().Accounts.add(account.get());

        return Either.left(userRepository.save(user.get()));
    }

    public Either<User, String> deleteAccountFromUser(int account_id, int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        Optional<Account> account = accountRepository.findById(account_id);

        if (user.isEmpty()) {
            return Either.right("User not found");
        } else if (account.isEmpty()) {
            return Either.right("Account not found");
        }

        user.get().Accounts = Utils.convertToVavrList(user.get().Accounts).filter(acc -> acc.Id != account.get().Id)
                .toJavaList();

        return Either.left(userRepository.save(user.get()));
    }

}