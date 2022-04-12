package com.db.user;

import com.db.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

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
    public void create(@RequestBody User user) throws InvalidUserException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        } else throw new InvalidUserException("Invalid!");
    }
}
