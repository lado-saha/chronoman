package com.minsih.chronoman.service;

import java.util.List;
import com.minsih.chronoman.model.PredefinedActivity;

public interface PredefinedActivityService {
    PredefinedActivity findById(Long id);
    
    List<PredefinedActivity> findAll();
    
    PredefinedActivity save(PredefinedActivity predefinedActivity);
    
    void deleteById(Long id);
}
