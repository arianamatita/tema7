package com.db.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findById(int id);

    User findByFirstName(String firstName);

    User findByEmail(String email);
}
