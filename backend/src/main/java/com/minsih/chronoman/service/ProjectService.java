package com.minsih.chronoman.service;

import java.util.List;
import com.minsih.chronoman.model.Project;

public interface ProjectService {
  Project findById(Long id);

  List<Project> findAll();

  Project save(Project project);

  void deleteById(Long id);
}
