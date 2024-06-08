package com.minsih.chronoman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.repository.SiteRepository;

@Service
public class SiteService {

  @Autowired
  private SiteRepository siteRepository;
  @Autowired
  private ActivityService activityService;

  public Page<Site> search(String query, Pageable pageable) {
    // new Site().getUser();
    return siteRepository.search(query, pageable);
  }

  public Site findById(Long id) {
    return siteRepository.findById(id).orElse(null);
  }

  public Page<Site> findByName(String name, Pageable pageable) {
    return siteRepository.findByNameIgnoreCase(name, pageable);
  }

  public Page<Site> findAll(Pageable pageable) {
    return siteRepository.findAll(pageable);
  }

  @Transactional()
  public Site create(Site site) {
    Site newSite = siteRepository.save(site);
    activityService.createFromDefaults(newSite);
    return newSite;
  }

  public Site save(Site site) {
    return siteRepository.save(site);
  }

  public void deleteById(Long id) {
    siteRepository.deleteById(id);
  }

  public Long count() {
    return siteRepository.count();
  }

  public List<Long> findAllSiteIds() {
    return siteRepository.findAllSiteIds();
  }

}
