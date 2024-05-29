package com.minsih.chronoman.service;

import java.util.List;
import com.minsih.chronoman.model.User;

public interface UserService {
    User findById(Long id);
    
    List<User> findAll();
    
    User save(User user);
    
    void deleteById(Long id);
}
