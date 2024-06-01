package com.minsih.chronoman.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
/**
 * Represents a project in the construction management system.
 * Each project has a name, description, start date, end date, status, budget,
 * stakeholders, and timestamps for creation and last update.
 */
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private Date startDate;

  private Date endDate;

  @Column(nullable = false, length = 50)
  private String status;

  @Column(precision = 15, scale = 2)
  private BigDecimal budget;

  @Column(nullable = false, length = 255)
  private String stakeholders;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  // Constructors, getters, and setters
}
