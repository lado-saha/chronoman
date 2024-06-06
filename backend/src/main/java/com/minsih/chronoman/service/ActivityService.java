package com.minsih.chronoman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.model.PredefinedActivity;
import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.repository.ActivityRepository;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository activityRepository;

  @Autowired
  private PredefinedActivityService defaultActivityService;

  @Autowired
  @Lazy
  private TaskService taskService;

  @Autowired
  @Lazy
  private SiteService siteService;

  /**
   * For a given site, we create all the associated Activities for the site
   */
  @Transactional
  public List<Activity> createFromDefaults(Site site) {
    List<PredefinedActivity> defaultActivities = defaultActivityService.findAll();
    return defaultActivities.stream().map(defActivity -> {
      Activity activity = save(
          new Activity(defActivity, site, "Please, modify the template "));
      taskService.createFromDefaults(activity);
      System.out.println(activity);
      return activity;
    }).toList();
  }

  public Activity findById(Long id) {
    return activityRepository.findById(id).orElse(null);
  }

  public List<Activity> findAll() {
    return activityRepository.findAll();
  }

  public Activity save(Activity activity) {
    Activity newActivity = activityRepository.save(activity);
    if (newActivity.getOldDuration() != newActivity.getDuration()) {
      int durationChange = newActivity.getDuration() - newActivity.getOldDuration();
      System.out.println("\nYo=" + durationChange);
      Site site = newActivity.getSite();
      site.setTotalActivitiesDuration(site.getTotalActivitiesDuration() + durationChange);
      // The duration should always be greater or equals to that of the combined
      // duration of its children
      // if (site.getDuration() < site.getTotalActivitiesDuration()) {
      site.setDuration(site.getTotalActivitiesDuration());
      // }
      siteService.save(site);
    }
    return newActivity;
  }

  public void deleteById(Long id) {
    activityRepository.deleteById(id);
  }

  public List<Activity> findBySiteId(Long siteId) {
    return activityRepository.findBySiteId(siteId);
  }

}