package com.minsih.chronoman.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.model.PredefinedTask;
import com.minsih.chronoman.model.Task;
import com.minsih.chronoman.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final PredefinedTaskService defaultTaskService;

  public TaskServiceImpl(TaskRepository taskRepository, PredefinedTaskService defaultTaskService) {
    this.taskRepository = taskRepository;
    this.defaultTaskService = defaultTaskService;
  }

  @Override
  public Task findById(Long id) {
    return taskRepository.findById(id).orElse(null);
  }

  @Override
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  @Override
  public Task save(Task task) {
    return taskRepository.save(task);
  }

  @Override
  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }

  @Override
  public List<Task> findByActivityId(Long activityId) {
    return taskRepository.findByActivityId(activityId);
  }

  /**
   * This creates a new tasks for a given activity with all the initial
   * configurations which should be edited b ythe user once he wnats to update
   */
  @Transactional
  public List<Task> createFromDefaults(Activity activity) {
    List<PredefinedTask> deafaultTasks = defaultTaskService.findAll();
    return deafaultTasks.stream().map(defTask -> {
      Task task = save(new Task(activity, defTask, "planned", 0, "Please, edit this template", null));
      System.out.println(task);
      return task;
    }).toList();
  }

}
