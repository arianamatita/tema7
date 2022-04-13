package com.db.businessLayer;

import com.db.account.AccountRepository;
import com.db.user.User;
import com.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class Streams {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping()
    public List<String> allUsersInitials() {
       List<String> initials = null;
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            initials.add(user.getFirstName() + " " + user.getLastName());
        }
//        String allInitials = initials.stream()
//                .map(str -> str.substring(0,1))
//                .collect(Collectors.joining());
        String allInitials = initials.stream()
                .map(str -> str.split(" "))
                .collect(Collectors.joining(" "));
    }
}
