package com.minsih.chronoman.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.minsih.chronoman.model.Project;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // You can define custom query methods here if needed
    Page<Project> findByNameIgnoreCase(String name, Pageable pageable);
}
