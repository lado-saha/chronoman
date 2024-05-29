package com.minsih.chronoman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minsih.chronoman.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed
}
