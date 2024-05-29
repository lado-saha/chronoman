package com.minsih.chronoman.service;

import java.util.List;
import com.minsih.chronoman.model.PredefinedTask;

public interface PredefinedTaskService {
    PredefinedTask findById(Long id);
    
    List<PredefinedTask> findAll();
    
    PredefinedTask save(PredefinedTask predefinedTask);
    
    void deleteById(Long id);

    List<PredefinedTask> findByActivityId(Long activityId);

}
