package com.db.user;

import com.db.InvalidException;
import com.db.UnauthorizedException;
import com.db.account.Account;
import com.db.account.AccountRepository;
import com.db.transferMoney.TransferDetails;
import com.db.transferMoney.TransferMoneyServiceExtern;
import com.db.transferMoney.TransferMoneyServiceIntern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserController {

    @Autowired
    @Qualifier("intern")
    TransferMoneyServiceIntern transferMoneyServiceIntern;

    @Autowired
    @Qualifier("extern")
    TransferMoneyServiceExtern transferMoneyServiceExtern;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @GetMapping("search")
    public List<User> getUserByFirstName(@RequestParam String firstName) {
        List<User> users = new ArrayList<>();
        Iterable<User> iterable = userRepository.findByFirstName(firstName);
        for (User user : iterable) {
            users.add(user);
        }
        return users;
    }

    @PostMapping()
    public void create(@RequestBody User user) throws InvalidException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        } else throw new InvalidException("Invalid!");
    }

    @GetMapping("/initials")
    public Stream<String> allUsersInitials() {
        List<String> initials = new ArrayList<>();
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            initials.add(user.getFirstName() + " " + user.getLastName());
        }
        return initials.stream().map(this::getInitials);
    }

    @GetMapping("/gmail")
    public long getNumberByGmail() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(user -> user.getEmail().contains("@gmail")).count();
    }

    @GetMapping("/containsA")
    public long getNumberByFirstName() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(user -> (user.getFirstName().contains("A") ||
                user.getFirstName().contains("a")) && user.getAge() < 20).count();
    }

    @GetMapping("/FirstNameInitials")
    public String getAllUsersFirstNameInitials() {
        List<User> users = (List<User>) userRepository.findAll();
        List<String> initials = users.stream().map(User::getFirstName)
                .collect(Collectors.toList());
        String joined = initials.stream().map(this::getFirstNameInitials)
                .collect(Collectors.joining());
        return joined;
    }

    @GetMapping("list")
    public List<String> listOfEachLastName() {
        List<User> users = (List<User>) userRepository.findAll();
        List<String> list = users.stream().map(User::getLastName)
                .distinct().collect(Collectors.toList());
        return list;
    }

    @GetMapping("/sendMoney")
    public void sendMoney(@RequestBody TransferDetails transferDetails) throws InvalidException {
        Account fromAccount = accountRepository.findByIban(transferDetails.getFrom());
        Account toAccount = accountRepository.findByIban(transferDetails.getTo());
        if (fromAccount.getIban().contains("INT") && toAccount.getIban().contains("INT")) {
            transferMoneyServiceIntern.executeTransfer(transferDetails.getAmount(), toAccount, fromAccount);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
        } else if (fromAccount.getIban().contains("EXT") && toAccount.getIban().contains("EXT")) {
            transferMoneyServiceExtern.executeTransfer(transferDetails.getAmount(), toAccount, fromAccount);
            accountRepository.save(toAccount);
        } else throw new InvalidException("Invalid Iban!");
    }

    private String getFirstNameInitials(String name) {
        String[] words = name.split(" ");
        name = String.valueOf(Character.toUpperCase(name.charAt(0)));
        return name;
    }

    private String getInitials(String name) {
        String[] words = name.split(" ");
        name = String.valueOf(Character.toUpperCase(
                name.charAt(0)));
        String name1 = "";
        name1 = String.valueOf(Character.toUpperCase(
                words[1].charAt(0)));
        name = name.concat(name1);
        return name;
    }


}
