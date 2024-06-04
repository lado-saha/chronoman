package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.model.PredefinedActivity;
import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

  private final ActivityRepository activityRepository;
  private final PredefinedActivityService defaultActivityService;
  private final TaskService taskService;

  public ActivityServiceImpl(ActivityRepository activityRepository, PredefinedActivityService defaultActivityService,
      TaskService taskService) {
    this.activityRepository = activityRepository;
    this.defaultActivityService = defaultActivityService;
    this.taskService = taskService;
  }

  /**
   * For a given site, we create all the associated Activities for the site
   */
  @Transactional
  public List<Activity> createFromDefaults(Site site) {
    List<PredefinedActivity> defaultActivities = defaultActivityService.findAll();
    return defaultActivities.stream().map(defActivity -> {
      Activity activity = save(new Activity(defActivity, site, "planned", 0, "Please, modify the template "));
      taskService.createFromDefaults(activity);
      System.out.println(activity);
      return activity;
    }).toList();
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