package com.minsih.chronoman.service;

import com.minsih.chronoman.model.Activity;
import java.util.List;

/**
 * ActivityService
 */
public interface ActivityService {
  Activity findById(Long id);

  List<Activity> findAll();

  Activity save(Activity activity);

  void deleteById(Long id);

  List<Activity> findBySiteId(Long siteId);
}