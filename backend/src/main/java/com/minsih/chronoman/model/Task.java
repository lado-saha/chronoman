package com.minsih.chronoman.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.minsih.chronoman.TaskEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
/**
 * Represents a task in the construction management system.
 * Each task is associated with an activity and a predefined task.
 * It has an ID, activity ID, predefined task ID, status, duration, comment, start date, real end date,
 * and timestamps for creation and last update.
 */
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
// @EntityListeners(TaskEntityListener.class)
public class Task {

  public Task() {
  }

  public Task(Activity activity, PredefinedTask predefinedTask, String comment) {
    this.activity = activity;
    this.predefinedTask = predefinedTask;
    this.comment = comment;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "activity_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Activity activity;

  @ManyToOne
  @JoinColumn(name = "predefined_task_id", nullable = false)
  private PredefinedTask predefinedTask;

  @Column(nullable = false)
  private int duration;

  @Column(columnDefinition = "TEXT")
  private String comment;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private ProjectStatus status = ProjectStatus.PLANNED;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private Date updatedAt;

  @Column(nullable = true)
  private int oldDuration;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date realStartDate;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date realEndDate;
  // Constructors, getters, and setters
}