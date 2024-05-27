package com.minsih.chronoman.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minsih.chronoman.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // You can define custom query methods here if needed
}
