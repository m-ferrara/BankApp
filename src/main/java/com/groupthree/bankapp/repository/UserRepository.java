package com.groupthree.bankapp.repository;

import com.groupthree.bankapp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    void delete(long id);
}
