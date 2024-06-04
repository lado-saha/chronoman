package com.minsih.chronoman.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.repository.SiteRepository;

@Service
public class SiteServiceImpl implements SiteService {

  private final SiteRepository siteRepository;
  private final ActivityService activityService;

  public SiteServiceImpl(SiteRepository siteRepository, ActivityService activityService) {
    this.siteRepository = siteRepository;
    this.activityService = activityService;
  }

  public Page<Site> search(String query, Pageable pageable) {
    return siteRepository.search(query, pageable);
  }

  @Override
  public Site findById(Long id) {
    return siteRepository.findById(id).orElse(null);
  }

  @Override
  public Page<Site> findByName(String name, Pageable pageable) {
    return siteRepository.findByNameIgnoreCase(name, pageable);
  }

  @Override
  public Page<Site> findAll(Pageable pageable) {
    return siteRepository.findAll(pageable);
  }

  @Transactional()
  @Override
  public Site save(Site site) {
    Site newSite = siteRepository.save(site);
    activityService.createFromDefaults(newSite);
    return newSite;
  }

  @Override
  public void deleteById(Long id) {
    siteRepository.deleteById(id);
  }

  @Override
  public Long count() {
    return siteRepository.count();
  }

}
