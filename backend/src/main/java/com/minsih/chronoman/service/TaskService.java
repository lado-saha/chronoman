package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.model.Task;

public interface TaskService {
  Task findById(Long id);

  List<Task> findAll();

  Task save(Task task);

  void deleteById(Long id);

  List<Task> findByActivityId(Long activityId);


  @Transactional
  List<Task> createFromDefaults(Activity activity);
}
