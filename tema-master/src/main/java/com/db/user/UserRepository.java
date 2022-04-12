package com.db.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findById(int id);

    List<User> findByFirstName(String firstName);

    User findByEmail(String email);
}
