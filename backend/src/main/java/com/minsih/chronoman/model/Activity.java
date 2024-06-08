package com.minsih.chronoman.model;

/**
 * Represents an activity in the construction management system.
 * Each activity is associated with a predefined activity and a construction site.
 * It has an ID, predefined activity ID, site ID, status, duration, comment, start date, real end date,
 * and timestamps for creation and last update.
 */
import lombok.Data;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.*;;

@Data
@Entity
// @EntityListeners(ActivityEntityListener.class)
@Table(name = "activities")
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "predefined_activity_id", nullable = false)
  private PredefinedActivity predefinedActivity;

  @ManyToOne
  @JoinColumn(name = "site_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Site site;

  @Column(nullable = false)
  private int duration;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private ProjectStatus status = ProjectStatus.PLANNED;

  @Column(columnDefinition = "TEXT")
  private String comment;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date realEndDate;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date realStartDate;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @Column(nullable = false)
  private int totalTasksDuration;

  /**
   * A temporary field
   */
  @Column(nullable = true)
  private int oldDuration;

  public Activity() {
  }

  public Activity(PredefinedActivity predefinedActivity, Site site,
      String comment) {
    this.predefinedActivity = predefinedActivity;
    this.site = site;
    this.comment = comment;
  }
  // Constructors, getters, and setters
}