package com.minsih.chronoman.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.minsih.chronoman.model.Site;

public interface SiteService {
  Site findById(Long id);

  @Transactional()
  Site save(Site site);

  void deleteById(Long id);

  Page<Site> findByName(String name, Pageable pageable);

  Page<Site> findAll(Pageable pageable);

  Page<Site> search(String query, Pageable pageable);

  Long count();
}
