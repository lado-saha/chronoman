package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.Task;
import com.minsih.chronoman.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
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
}
