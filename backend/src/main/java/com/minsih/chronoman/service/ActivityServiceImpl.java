package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

  private final ActivityRepository activityRepository;

  public ActivityServiceImpl(ActivityRepository activityRepository) {
    this.activityRepository = activityRepository;
  }

  @Override
  public Activity findById(Long id) {
    return activityRepository.findById(id).orElse(null);
  }

  @Override
  public List<Activity> findAll() {
    return activityRepository.findAll();
  }

  @Override
  public Activity save(Activity activity) {
    return activityRepository.save(activity);
  }

  @Override
  public void deleteById(Long id) {
    activityRepository.deleteById(id);
  }

  @Override
  public List<Activity> findBySiteId(Long siteId) {
    return activityRepository.findBySiteId(siteId);
  }
}
