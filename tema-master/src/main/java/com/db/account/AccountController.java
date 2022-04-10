package com.db.account;

import com.db.user.InvalidUserException;
import com.db.user.User;
import com.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("account/{currency}/user/{id}")
    public void createAccount(@PathVariable String currency, @PathVariable int id) throws InvalidUserException {
        User user = userRepository.findById(id);
        if (user != null) {
            Account account = new Account(user ,currency);
            account.setIban(account.generateIban());
            accountRepository.save(account);
        }
        else throw new InvalidUserException("Invalid user!");
    }

    @GetMapping("accounts/{id}")
    public List<Account> getUserAccounts(@PathVariable int id) throws InvalidUserException{
        List<Account> accounts = new ArrayList<>();
        User user = userRepository.findById(id);
        if (user != null) {
            Iterable<Account> allAccounts = accountRepository.findByUserId(id);
            for (Account account : allAccounts) {
                accounts.add(account);
            }
        }
        return accounts;
    }
}
