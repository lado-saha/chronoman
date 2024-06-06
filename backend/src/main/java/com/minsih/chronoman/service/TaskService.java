package com.minsih.chronoman.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.model.PredefinedTask;
import com.minsih.chronoman.model.Task;
import com.minsih.chronoman.repository.TaskRepository;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  private PredefinedTaskService defaultTaskService;
  @Autowired
  private ActivityService activityService;

  // public TaskService(TaskRepository taskRepository, PredefinedTaskService
  // defaultTaskService) {
  // this.taskRepository = taskRepository;
  // this.defaultTaskService = defaultTaskService;
  // }

  public Task findById(Long id) {
    return taskRepository.findById(id).orElse(null);
  }

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  public Task save(Task task) {
    Task newTask = taskRepository.save(task);
    if (newTask.getDuration() != newTask.getOldDuration()) {
      int durationChange = newTask.getDuration() - newTask.getOldDuration();
      Activity activity = newTask.getActivity();
      activity.setTotalTasksDuration(activity.getTotalTasksDuration() + durationChange);
      // The duration should always be greater or equals to that of the combined
      // duration of its children
      // if (activity.getDuration() < activity.getTotalTasksDuration()) {
      activity.setOldDuration(activity.getDuration());
      activity.setDuration(activity.getTotalTasksDuration());
      // }
      activityService.save(activity);
    }
    return newTask;
  }

  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }

  public List<Task> findByActivityId(Long activityId) {
    return taskRepository.findByActivityId(activityId);
  }

  /**
   * This creates a new tasks for a given activity with all the initial
   * configurations which should be edited b ythe user once he wnats to update
   */
  @Transactional
  public List<Task> createFromDefaults(Activity activity) {
    List<PredefinedTask> deafaultTasks = defaultTaskService.findByActivityId(activity.getId());
    return deafaultTasks.stream().map(defTask -> {
      Task task = save(new Task(activity, defTask, "Please, edit this template"));
      System.out.println(task);
      return task;
    }).toList();
  }

}
