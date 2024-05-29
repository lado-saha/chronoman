package com.minsih.chronoman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.minsih.chronoman.model.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    // You can define custom query methods here if needed
    List<Site> findByProjectId(Long projectId);
}
