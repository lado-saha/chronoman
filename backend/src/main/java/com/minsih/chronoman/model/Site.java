package com.minsih.chronoman.model;


/**
 * Represents a construction site in the construction management system.
 * Each site belongs to a project and is located at a specific location.
 * It has a name, start date, estimation duration, status, and timestamps
 * for creation and last update.
 */
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sites")
public class Site {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = false)
  private Date startDate;

  @Column(nullable = false)
  private int estimationDuration;

  @Column(nullable = false, length = 50)
  private String status;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  // Constructors, getters, and setters
}
