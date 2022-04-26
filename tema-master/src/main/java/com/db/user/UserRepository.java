package com.db.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findById(int id);

    List<User> findByFirstName(String firstName);

    User findByEmail(String email);

    Optional<User> findByEmailAndPassword(String username, String password);

}
