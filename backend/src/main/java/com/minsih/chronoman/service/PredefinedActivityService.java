package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.PredefinedActivity;
import com.minsih.chronoman.repository.PredefinedActivityRepository;

@Service
public class PredefinedActivityService {

    private final PredefinedActivityRepository predefinedActivityRepository;

    public PredefinedActivityService(PredefinedActivityRepository predefinedActivityRepository) {
        this.predefinedActivityRepository = predefinedActivityRepository;
    }

    public PredefinedActivity findById(Long id) {
        return predefinedActivityRepository.findById(id).orElse(null);
    }

    public List<PredefinedActivity> findAll() {
        return predefinedActivityRepository.findAll();
    }

    public PredefinedActivity save(PredefinedActivity predefinedActivity) {
        return predefinedActivityRepository.save(predefinedActivity);
    }

    public void deleteById(Long id) {
        predefinedActivityRepository.deleteById(id);
    }
}
