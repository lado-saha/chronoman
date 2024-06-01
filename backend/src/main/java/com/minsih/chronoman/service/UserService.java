package com.minsih.chronoman.service;

import java.util.List;
import com.minsih.chronoman.model.User;

public interface UserService {
    User findById(String id);

    User findByEmail(String email);

    List<User> findAll();

    User save(User user);

    void deleteById(String id);
}
