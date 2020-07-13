package com.pod32g.Demo.Controllers;

import java.util.List;
import java.util.Map;

import com.pod32g.Demo.Models.Account;
import com.pod32g.Demo.Services.AccountService;
import com.pod32g.Demo.Utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.vavr.control.Either;

@Api(value = "Account API", description = "CRUD for the Accounts")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    @ApiOperation(value = "Get all Accounts", notes = "Returns a list with all Accounts available")
    public Map<String, List<Account>> getAllAccounts() {
        return Utils.generateSimpleResponse("Accounts", accountService.getAllAccounts());
    }

    @GetMapping("/accounts/{id}")
    @ApiOperation(value = "Get Account", notes = "Returns an Account")
    public Map<String, Object> getAccount(@PathVariable("id") int id) {
        Either<Account, String> account = accountService.getAccountByID(id);

        if (account.isRight()) {
            return Utils.generateSimpleResponse("Error", account.get());
        }

        return Utils.generateSimpleResponse("Account", account.getLeft());
    }

    @DeleteMapping("/accounts/{id}")
    @ApiOperation(value = "Delete Account", notes = "Deletes an available Account")
    public Map<String, Object> deleteAccount(@PathVariable("id") int id) {
        Either<Boolean, String> account = accountService.deleteById(id);

        if (account.isRight()) {
            return Utils.generateSimpleResponse("Error", account.get());
        }

        return Utils.generateSimpleResponse("Deleted", account.getLeft());
    }

    @PostMapping("/accounts")
    @ApiOperation(value = "Save account", notes = "Add/Update an Account")
    public Map<String, Account> saveAccount(@RequestBody Account account) {
        return Utils.generateSimpleResponse("Saved", accountService.updateOrSave(account));
    }
}