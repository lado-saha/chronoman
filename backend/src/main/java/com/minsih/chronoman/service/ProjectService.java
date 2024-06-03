package com.minsih.chronoman.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.minsih.chronoman.model.Project;

public interface ProjectService {
  Project findById(Long id);

  Page<Project> findByName(String name, Pageable pageable);

  Page<Project> findAll(Pageable pageable);

  Project save(Project project);

  void deleteById(Long id);

  Long count();
}
