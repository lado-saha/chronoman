package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.PredefinedActivity;
import com.minsih.chronoman.repository.PredefinedActivityRepository;

@Service
public class PredefinedActivityServiceImpl implements PredefinedActivityService {

    private final PredefinedActivityRepository predefinedActivityRepository;

    public PredefinedActivityServiceImpl(PredefinedActivityRepository predefinedActivityRepository) {
        this.predefinedActivityRepository = predefinedActivityRepository;
    }

    @Override
    public PredefinedActivity findById(Long id) {
        return predefinedActivityRepository.findById(id).orElse(null);
    }

    @Override
    public List<PredefinedActivity> findAll() {
        return predefinedActivityRepository.findAll();
    }

    @Override
    public PredefinedActivity save(PredefinedActivity predefinedActivity) {
        return predefinedActivityRepository.save(predefinedActivity);
    }

    @Override
    public void deleteById(Long id) {
        predefinedActivityRepository.deleteById(id);
    }
}
