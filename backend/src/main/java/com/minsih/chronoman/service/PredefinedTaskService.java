package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.PredefinedTask;
import com.minsih.chronoman.repository.PredefinedTaskRepository;

@Service
public class PredefinedTaskService  {

  private final PredefinedTaskRepository predefinedTaskRepository;

  public PredefinedTaskService(PredefinedTaskRepository predefinedTaskRepository) {
    this.predefinedTaskRepository = predefinedTaskRepository;
  }

  
  public PredefinedTask findById(Long id) {
    return predefinedTaskRepository.findById(id).orElse(null);
  }

  
  public List<PredefinedTask> findAll() {
    return predefinedTaskRepository.findAll();
  }

  
  public PredefinedTask save(PredefinedTask predefinedTask) {
    return predefinedTaskRepository.save(predefinedTask);
  }

  
  public void deleteById(Long id) {
    predefinedTaskRepository.deleteById(id);
  }

  
  public List<PredefinedTask> findByActivityId(Long activityId) {
    return predefinedTaskRepository.findByActivityId(activityId);
  }
}
