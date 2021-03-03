package com.groupthree.bankapp.service;

import com.groupthree.bankapp.entity.User;

public interface UserService {
    void delete(long id);

    User save(User user);
}
