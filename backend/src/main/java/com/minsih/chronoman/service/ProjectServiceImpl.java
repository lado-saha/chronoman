package com.minsih.chronoman.service;

import org.springframework.stereotype.Service;

import com.minsih.chronoman.model.Project;
import com.minsih.chronoman.repository.ProjectRepository;

import java.util.List;

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
  public List<Project> findAll() {
    return projectRepository.findAll();
  }

  @Override
  public Project save(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public void deleteById(Long id) {
    projectRepository.deleteById(id);
  }
}
