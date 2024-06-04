package com.minsih.chronoman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.minsih.chronoman.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // You can define custom query methods here if needed
    List<Task> findByActivityId(Long activityId);

}
