package com.minsih.chronoman.service;

import java.util.List;
import com.minsih.chronoman.model.Site;

public interface SiteService {
  Site findById(Long id);

  List<Site> findAll();

  Site save(Site site);

  void deleteById(Long id);
}
