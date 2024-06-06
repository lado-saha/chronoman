package com.minsih.chronoman.model;

/**
 * Represents a construction site in the construction management system.
 * Each site belongs to a project and is located at a specific location.
 * It has a name, start date, estimation duration, status, and timestamps
 * for creation and last update.
 */
import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.minsih.chronoman.TaskEntityListener;

import java.util.*;

@Data
@Table(name = "sites")
@Entity
public class Site {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private ProjectStatus status = ProjectStatus.PLANNED;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date realStartDate;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date realEndDate;

  @Column(nullable = false)
  private int duration;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private Date updatedAt;

  @Column(precision = 15, scale = 2)
  private BigDecimal budget;

  @Column(nullable = false, length = 255)
  private String stakeholders;

  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;

  @Column(nullable = false, length = 255)
  private String town;

  @Column(nullable = false, length = 255)
  private String country;
  @Column(nullable = false, length = 255)
  private String region;
  @Column(nullable = true)
  private String description;
  // Constructors, getters, and setters

  @Column(nullable = false)
  private int totalActivitiesDuration ;

}
