package com.db.account;

import com.db.InvalidUserException;
import com.db.user.User;
import com.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("user/{id}/account")
    public void createAccount(@PathVariable int id, @RequestParam String currency, @RequestParam String type) throws InvalidUserException {
        User user = userRepository.findById(id);
        if (user != null) {
            Account account = new Account(user, currency);
            account.setIban(account.generateIban(type));
            accountRepository.save(account);
        } else throw new InvalidUserException("Invalid user!");
    }

    @GetMapping("user/{id}/accounts")
    public List<Account> getUserAccounts(@PathVariable int id) throws InvalidUserException {
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
