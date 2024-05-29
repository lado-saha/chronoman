package com.minsih.chronoman.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.minsih.chronoman.model.Location;
import com.minsih.chronoman.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;

  public LocationServiceImpl(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  @Override
  public Location findById(Long id) {
    return locationRepository.findById(id).orElse(null);
  }

  @Override
  public List<Location> findAll() {
    return locationRepository.findAll();
  }

  @Override
  public Location save(Location location) {
    return locationRepository.save(location);
  }

  @Override
  public void deleteById(Long id) {
    locationRepository.deleteById(id);
  }
}
