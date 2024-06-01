package com.minsih.chronoman.model;


/**
 * Represents a task in the construction management system.
 * Each task is associated with an activity and a predefined task.
 * It has an ID, activity ID, predefined task ID, status, duration, comment, start date, real end date,
 * and timestamps for creation and last update.
 */
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "activity_id", nullable = false)
  private Activity activity;

  @ManyToOne
  @JoinColumn(name = "predefined_task_id", nullable = false)
  private PredefinedTask predefinedTask;

  @Column(nullable = false, length = 50)
  private String status;

  @Column(nullable = false)
  private int duration;

  @Column(columnDefinition = "TEXT")
  private String comment;

  @Column(nullable = false)
  private Date startDate;

  private Date realEndDate;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private Date updatedAt;

  // Constructors, getters, and setters
}