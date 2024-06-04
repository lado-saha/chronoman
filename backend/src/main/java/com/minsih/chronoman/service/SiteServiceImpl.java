package com.minsih.chronoman.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.repository.SiteRepository;

@Service
public class SiteServiceImpl implements SiteService {

  private final SiteRepository siteRepository;

  public SiteServiceImpl(SiteRepository siteRepository) {
    this.siteRepository = siteRepository;
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

  @Override
  public Site save(Site site) {
    return siteRepository.save(site);
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
