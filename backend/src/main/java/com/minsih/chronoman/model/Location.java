package com.minsih.chronoman.model;


/**
 * Represents a location in the construction management system.
 * Each location has a name, latitude, longitude, region, town, country,
 * and timestamps for creation and last update.
 */
import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;

@Data
@Entity
@Table(name = "locations")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;

  @Column(nullable = false, length = 255)
  private String town;

  @Column(nullable = false, length = 255)
  private String country;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  // Constructors, getters, and setters
}
