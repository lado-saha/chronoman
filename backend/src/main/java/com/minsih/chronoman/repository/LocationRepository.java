package com.minsih.chronoman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minsih.chronoman.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // You can define custom query methods here if needed
}


