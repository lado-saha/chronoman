package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.repository.SiteRepository;

@Service
public class SiteServiceImpl implements SiteService {

    private final SiteRepository siteRepository;

    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public Site findById(Long id) {
        return siteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Site> findAll() {
        return siteRepository.findAll();
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
    public List<Site> findByProjectId(Long projectId) {
        return siteRepository.findByProjectId(projectId);
    }
}
