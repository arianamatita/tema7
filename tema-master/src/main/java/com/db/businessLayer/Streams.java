package com.db.businessLayer;

import com.db.account.AccountRepository;
import com.db.user.User;
import com.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
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
