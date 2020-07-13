package com.pod32g.Demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.vavr.control.Either;

import com.pod32g.Demo.Services.UserService;
import com.pod32g.Demo.Utils.Utils;

import java.util.List;
import java.util.Map;

import com.pod32g.Demo.Models.User;

class JoinAccount {

    @ApiModelProperty(value = "The User's id", required = true)
    public int User;
    @ApiModelProperty(value = "The Account id", required = true)
    public int Account;
}

@Api(value = "User API", description = "CRUD for the Users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @ApiOperation(value = "Get all users", notes = "Returns a list with all users available")
    public Map<String, List<User>> getAllUsers() {
        return Utils.generateSimpleResponse("Users", userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "Get user", notes = "Returns an user by a given Id")
    public Map<String, Object> getUser(@PathVariable("id") int id) {
        Either<User, String> user = userService.getUserById(id);

        if (user.isRight()) {
            return Utils.generateSimpleResponse("Error", user.get());
        }

        return Utils.generateSimpleResponse("User", user.getLeft());
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation(value = "Delete User", notes = "Deletes an user by a given Id")
    public Map<String, Object> deleteUser(@PathVariable("id") int id) {
        Either<Boolean, String> deleted = userService.deleteById(id);

        if (deleted.isRight()) {
            return Utils.generateSimpleResponse("Error", deleted.get());
        }

        return Utils.generateSimpleResponse("Deleted", deleted.getLeft());
    }

    @PostMapping("/users")
    @ApiOperation(value = "Save User", notes = "Adds/Updates an user")
    public Map<String, User> saveUser(@RequestBody User user) {
        return Utils.generateSimpleResponse("Saved", userService.updateOrSave(user));
    }

    @PostMapping("/users/account")
    @ApiOperation(value = "Update User with account", notes = "Adds the account to the user's account list")
    public Map<String, Object> updateUserWithAccount(@RequestBody JoinAccount request) {
        Either<User, String> user = userService.addAccountToUser(request.Account, request.User);

        if (user.isRight()) {
            return Utils.generateSimpleResponse("Error", user.get());
        }

        return Utils.generateSimpleResponse("Updated", user.getLeft());
    }

    @DeleteMapping("/users/account")
    @ApiOperation(value = "Delete Account from User", notes = "Deletes an account from the user's account list")
    public Map<String, Object> deleteAccountFromUser(@RequestBody JoinAccount request) {
        Either<User, String> user = userService.deleteAccountFromUser(request.Account, request.User);

        if (user.isRight()) {
            return Utils.generateSimpleResponse("Error", user.get());
        }

        return Utils.generateSimpleResponse("Updated", user.getLeft());
    }
}