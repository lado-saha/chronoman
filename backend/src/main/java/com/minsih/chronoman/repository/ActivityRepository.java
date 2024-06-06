package com.minsih.chronoman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.minsih.chronoman.model.Activity;
import java.util.*;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // You can define custom query methods here if needed
    // Automatically gets it
    List<Activity> findBySiteId(Long siteId);

    // @Query("SELECT SUM(t.duration) FROM tasks t WHERE t.activity.id= :activityId")
    // Integer totalTaskDurationByActitivityId(Long activityId);

   
}
