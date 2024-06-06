package com.minsih.chronoman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.minsih.chronoman.model.Task;
import com.minsih.chronoman.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  @Autowired
  @Lazy
  private TaskService taskService;


  @GetMapping
  public ResponseEntity<List<Task>> getAllTasks() {
    List<Task> tasks = taskService.findAll();
    return new ResponseEntity<>(tasks, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
    Task task = taskService.findById(id);
    return task != null ? new ResponseEntity<>(task, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task createdTask = taskService.save(task);
    return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
    Task existingTask = taskService.findById(id);
    if (existingTask != null) {
      task.setId(id);
      task.setOldDuration(existingTask.getDuration());
      Task updatedTask = taskService.save(task);
      return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    Task task = taskService.findById(id);
    if (task != null) {
      taskService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
