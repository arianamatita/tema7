package com.db.user;

import com.db.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @GetMapping("search/{firstName}")
    public User getUserByFirstName(@PathVariable String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @PostMapping("/create")
    public void create(@RequestBody User user) throws InvalidUserException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        } else throw new InvalidUserException("Invalid!");
    }
}
