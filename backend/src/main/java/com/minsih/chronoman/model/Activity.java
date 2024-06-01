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
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;;

@Data
@Entity
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
  private Site site;

  @Column(nullable = false, length = 50)
  private String status;

  @Column(nullable = false)
  private int duration;

  @Column(columnDefinition = "TEXT")
  private String comment;

  @Column(nullable = false)
  private Date startDate;

  private Date realEndDate;

 
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;



  // Constructors, getters, and setters
}