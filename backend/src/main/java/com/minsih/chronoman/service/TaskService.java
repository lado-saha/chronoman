package com.minsih.chronoman.service;

import java.util.List;
import com.minsih.chronoman.model.Task;

public interface TaskService {
  Task findById(Long id);

  List<Task> findAll();

  Task save(Task task);

  void deleteById(Long id);

  List<Task> findByActivityId(Long activityId);
}
