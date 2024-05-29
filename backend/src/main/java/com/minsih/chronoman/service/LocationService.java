package com.minsih.chronoman.service;

import java.util.List;
import com.minsih.chronoman.model.Location;

public interface LocationService {
    Location findById(Long id);

    List<Location> findAll();

    Location save(Location location);

    void deleteById(Long id);
}
