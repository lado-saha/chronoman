package com.minsih.chronoman.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.minsih.chronoman.model.Project;
import com.minsih.chronoman.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;

  public ProjectServiceImpl(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public Project findById(Long id) {
    return projectRepository.findById(id).orElse(null);
  }

  @Override
  public Page<Project> findByName(String name, Pageable pageable) {
    return projectRepository.findByNameIgnoreCase(name, pageable);
  }

  @Override
  public Page<Project> findAll(Pageable pageable) {
    return projectRepository.findAll(pageable);
  }

  @Override
  public Project save(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public void deleteById(Long id) {
    projectRepository.deleteById(id);
  }

  @Override
  public Long count() {
    return projectRepository.count();
  }
}
