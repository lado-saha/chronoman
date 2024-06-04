package com.minsih.chronoman.service;

import com.minsih.chronoman.model.Activity;
import com.minsih.chronoman.model.Site;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * ActivityService
 */
public interface ActivityService {
  Activity findById(Long id);

  List<Activity> findAll();

  Activity save(Activity activity);

  @Transactional
  List<Activity> createFromDefaults(Site site);

  void deleteById(Long id);

  List<Activity> findBySiteId(Long siteId);

}