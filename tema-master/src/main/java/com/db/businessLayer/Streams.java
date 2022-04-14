package com.db.businessLayer;

import com.db.account.AccountRepository;
import com.db.user.User;
import com.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class Streams {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

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
