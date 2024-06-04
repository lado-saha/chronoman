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
import java.util.*;;

@Data
@Entity
@Table(name = "sites")
public class Site {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = false)
  private Date startDate;

  @Column(nullable = false)
  private int duration;

  @Column(nullable = false, length = 50)
  private String status;

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

}
