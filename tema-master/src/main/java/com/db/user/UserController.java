package com.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public void create(@RequestBody User user) throws InvalidUserException{
        System.out.println(userRepository.findByEmail(user.getEmail()));
       if(userRepository.findByEmail(user.getEmail()) == null ){
           userRepository.save(user);
       }
       else throw new InvalidUserException("Invalid!");
    }
}
