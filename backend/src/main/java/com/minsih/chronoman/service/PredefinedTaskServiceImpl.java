package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.PredefinedTask;
import com.minsih.chronoman.repository.PredefinedTaskRepository;

@Service
public class PredefinedTaskServiceImpl implements PredefinedTaskService {

  private final PredefinedTaskRepository predefinedTaskRepository;

  public PredefinedTaskServiceImpl(PredefinedTaskRepository predefinedTaskRepository) {
    this.predefinedTaskRepository = predefinedTaskRepository;
  }

  @Override
  public PredefinedTask findById(Long id) {
    return predefinedTaskRepository.findById(id).orElse(null);
  }

  @Override
  public List<PredefinedTask> findAll() {
    return predefinedTaskRepository.findAll();
  }

  @Override
  public PredefinedTask save(PredefinedTask predefinedTask) {
    return predefinedTaskRepository.save(predefinedTask);
  }

  @Override
  public void deleteById(Long id) {
    predefinedTaskRepository.deleteById(id);
  }

  @Override
  public List<PredefinedTask> findByActivityId(Long activityId) {
    return predefinedTaskRepository.findByActivityId(activityId);
  }
}
